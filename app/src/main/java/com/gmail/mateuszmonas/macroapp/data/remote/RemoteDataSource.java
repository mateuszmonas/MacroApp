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
public class RemoteDataSource implements DataSource {

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
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback) {
        ServerQuery query = new ServerQuery("q1", "select REFERENCE, KOD, NAZ, NIP, KOLOR from KH order by kod offset 0 rows fetch next 10 rows only");
        Call<ServerResponseKontrahenci> call = api.getKontrahenci(query);
        call.enqueue(callback);
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference) {
        ServerQuery query = new ServerQuery("q1", "select faks.naz, faks.ul, faks.tz, faks.netto, faks.vat, faks.brutto, han.naz as han from faks join han on han.reference=faks.han where kh='" + kontrahentReference + "' order by d offset 0 rows FETCH NEXT 10 ROWS ONLY");
        Call<ServerResponseFaktury> call = api.getFaktury(query);
        call.enqueue(callback);
    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseKontrahenci> getKontrahenci(@Body ServerQuery query);

        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseFaktury> getFaktury(@Body ServerQuery query);
    }
}