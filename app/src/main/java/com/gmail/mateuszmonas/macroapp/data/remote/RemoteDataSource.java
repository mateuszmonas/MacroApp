package com.gmail.mateuszmonas.macroapp.data.remote;

import android.util.Log;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
    public void getKontrahenci(Callback<String> callback) {
        ApiEndpoint api = retrofit.create(ApiEndpoint.class);
        Call<String> call = api.getKontrahenci("{\"exec\":[{\"@id\":\"q1\",\"sql\":\"select KOD,NAZ,NIP from KH\"}]}");
        call.enqueue(callback);
    }

    @Override
    public <T> T parseJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<String> getKontrahenci(@Body String query);
    }
}