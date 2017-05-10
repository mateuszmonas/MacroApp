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

    @Inject
    RemoteDataSource(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient) {
        this.retrofit = retrofit;
        this.gson = gson;
        this.okHttpClient = okHttpClient;
    }

    @Override
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback) {
        ApiEndpoint api = retrofit.create(ApiEndpoint.class);
        Call<ServerResponseKontrahenci> call = api.getKontrahenci("{\"exec\":[{\"@id\":\"q1\",\"sql\":\"select KOD,NAZ,NIP,KOLOR from KH\"}]}");
        call.enqueue(callback);
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback) {

    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<ServerResponseKontrahenci> getKontrahenci(@Body String query);
    }
}