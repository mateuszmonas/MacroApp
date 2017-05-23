package com.gmail.mateuszmonas.macroapp.data.remote;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.DetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;
import com.gmail.mateuszmonas.macroapp.fakturasearch.FakturaSearchParameters;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@Singleton
class RemoteDataSource implements DataSource {

    private final Retrofit retrofit;
    private final Gson gson;
    private final OkHttpClient okHttpClient;
    private final ApiEndpoint api;

    @Inject
    RemoteDataSource(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient) {
        this.retrofit = retrofit;
        this.gson = gson;
        this.okHttpClient = okHttpClient;
        this.api = retrofit.create(ApiEndpoint.class);
    }

    @Override
    public void getKontrahenci(final CallbackServerResponse<List<Kontrahent>> callback, int offset, String nazwa) {
        ServerQuery query = new ServerQuery("select REFERENCE, KOD, NAZ, NIP, KOLOR from KH where lower(naz) like lower('%" + nazwa + "%') order by kod offset " + offset + " rows fetch next 10 rows only");
        Call<ServerResponseKontrahenci> call = api.getKontrahenci(query);
        call.enqueue(new Callback<ServerResponseKontrahenci>() {
            @Override
            public void onResponse(Call<ServerResponseKontrahenci> call, Response<ServerResponseKontrahenci> response) {
                callback.onResponse(response.body().getQ1().getData());
            }

            @Override
            public void onFailure(Call<ServerResponseKontrahenci> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    //// TODO: 2017-05-23 Nie można porównać wartości łańcuchowej i daty
    @Override
    public void getFaktury(final CallbackServerResponse<List<Faktura>> callback, String kontrahentReference, int offset, FakturaSearchParameters searchParameters) {
        ServerQuery query = new ServerQuery("select faks.reference, faks.sym, faks.brutto, faks.netto, faks.tz, faks.vat, han.naz " +
                "from faks join han on han.reference=faks.han " +
                "where lower(faks.sym) like lower('%" + searchParameters.getSymbol() + "%') " +
                "and faks.brutto>" + searchParameters.getCenaMin() + " " +
                "and faks.brutto<" + searchParameters.getCenaMax() + " " +
                //"and faks.tz>'" + searchParameters.getDateMin() + "' " +
                //"and faks.tz<'" + searchParameters.getDateMax() + "' " +
                "and kh='" + kontrahentReference + "' " +
                "order by tz " +
                "offset " + offset + " rows FETCH NEXT 10 ROWS ONLY");
        Call<ServerResponseFaktury> call = api.getFaktury(query);
        call.enqueue(new Callback<ServerResponseFaktury>() {
            @Override
            public void onResponse(Call<ServerResponseFaktury> call, Response<ServerResponseFaktury> response) {
                callback.onResponse(response.body().getQ1().getData());
            }

            @Override
            public void onFailure(Call<ServerResponseFaktury> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getDetaleFaktury(final CallbackServerResponse<DetaleFaktury> detaleFakturyCallback, final CallbackServerResponse<List<PozycjaFaktury>> pozycjeFakturyCallback, String fakturaReference) {
        ServerQuery detaleFakturyQuery = new ServerQuery("select faks.brutto, faks.netto, faks.vat, kh.naz, kh.ul, kh.miasto, kh.kpocz, kh.nip from faks join kh on kh.reference=faks.kh where faks.reference='" + fakturaReference + "'");
        Call<ServerResponseDetaleFaktury> detaleFakturyCall = api.getDetaleKontrahenta(detaleFakturyQuery);
        detaleFakturyCall.enqueue(new Callback<ServerResponseDetaleFaktury>() {
            @Override
            public void onResponse(Call<ServerResponseDetaleFaktury> call, Response<ServerResponseDetaleFaktury> response) {
                detaleFakturyCallback.onResponse(response.body().getQ1().getData().get(0));
            }

            @Override
            public void onFailure(Call<ServerResponseDetaleFaktury> call, Throwable t) {
                detaleFakturyCallback.onFailure();
            }
        });
        ServerQuery pozycjeFakturyQuery = new ServerQuery("select m.n, fap.cn, fap.il, fap.poz, fap.wn, fap.wb, fap.wv, jm.naz from fap join jm on jm.reference=fap.jm join m on m.reference=fap.m where fap.faks='" + fakturaReference + "'");
        Call<ServerResponsePozycjeFaktury> pozycjeFakturyCall = api.getPozycjeFaktury(pozycjeFakturyQuery);
        pozycjeFakturyCall.enqueue(new Callback<ServerResponsePozycjeFaktury>() {
            @Override
            public void onResponse(Call<ServerResponsePozycjeFaktury> call, Response<ServerResponsePozycjeFaktury> response) {
                pozycjeFakturyCallback.onResponse(response.body().getQ1().getData());
            }

            @Override
            public void onFailure(Call<ServerResponsePozycjeFaktury> call, Throwable t) {
                detaleFakturyCallback.onFailure();
            }
        });
    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseKontrahenci> getKontrahenci(@Body ServerQuery query);

        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseFaktury> getFaktury(@Body ServerQuery query);

        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponsePozycjeFaktury> getPozycjeFaktury(@Body ServerQuery query);

        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseDetaleFaktury> getDetaleKontrahenta(@Body ServerQuery query);
    }
}