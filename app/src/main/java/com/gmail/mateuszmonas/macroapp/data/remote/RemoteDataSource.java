package com.gmail.mateuszmonas.macroapp.data.remote;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@Singleton
class RemoteDataSource implements DataSource {

    private Retrofit retrofit;
    private Gson gson;
    private OkHttpClient okHttpClient;
    private ApiEndpoint api;

    @Inject
    RemoteDataSource(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient) {
        this.retrofit = retrofit;
        this.gson = gson;
        this.okHttpClient = okHttpClient;
        this.api = retrofit.create(ApiEndpoint.class);
    }

    @Override
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback, int offset, String nazwa) {
        ServerQuery query = new ServerQuery("select REFERENCE, KOD, NAZ, NIP, KOLOR from KH where lower(naz) like lower('%" + nazwa + "%') order by kod offset " + offset + " rows fetch next 10 rows only");
        Call<ServerResponseKontrahenci> call = api.getKontrahenci(query);
        call.enqueue(callback);
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference, int offset) {
        ServerQuery query = new ServerQuery("select faks.reference, faks.sym, faks.brutto, faks.netto, faks.tz, faks.vat, han.naz from faks join han on han.reference=faks.han where faks.brutto>0 and  kh='" + kontrahentReference + "' order by d offset " + offset + " rows FETCH NEXT 10 ROWS ONLY");
        Call<ServerResponseFaktury> call = api.getFaktury(query);
        call.enqueue(callback);
    }

    @Override
    public void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference) {
        ServerQuery detaleFakturyQuery = new ServerQuery("select faks.brutto, faks.netto, faks.vat, kh.naz, kh.ul, kh.miasto, kh.kpocz, kh.nip from faks join kh on kh.reference=faks.kh where faks.reference='" + fakturaReference + "'");
        Call<ServerResponseDetaleFaktury> detaleFakturyCall = api.getDetaleKontrahenta(detaleFakturyQuery);
        detaleFakturyCall.enqueue(detaleFakturyCallback);
        ServerQuery pozycjeFakturyQuery = new ServerQuery("select m.n, fap.cn, fap.il, fap.poz, fap.wn, fap.wb, fap.wv, jm.naz from fap join jm on jm.reference=fap.jm join m on m.reference=fap.m where fap.faks='" + fakturaReference + "'");
        Call<ServerResponsePozycjeFaktury> pozycjeFakturyCall = api.getPozycjeFaktury(pozycjeFakturyQuery);
        pozycjeFakturyCall.enqueue(pozycjeFakturyCallback);
    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseKontrahenci> getKontrahenci(@Body ServerQuery query);

        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseKontrahenci> searchKontrahenci(@Body ServerQuery query);

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