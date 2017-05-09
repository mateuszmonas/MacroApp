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
    public List<Kontrahent> getKontrahenci() {
        ApiEndpoint api = retrofit.create(ApiEndpoint.class);
        Call<String> call = api.getKontrahenci("{\"exec\":[{\"@id\":\"q1\",\"sql\":\"select KOD,NAZ,NIP from KH\"}]}");
        final List<Kontrahent> kontrahenci = new ArrayList<>();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                //i have to parse json response manually
                //because for some reason retrofit doesn't do it automatically
                ServerResponse serverResponse = gson.fromJson(response.body(), ServerResponse.class);
                for (Kontrahent k : serverResponse.getQ1().getData()){
                    kontrahenci.add(k);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });


        return kontrahenci;
    }

    interface ApiEndpoint {
        @Headers("content-type: application/json")
        @POST("ProcExec/batch-query")
        Call<String> getKontrahenci(@Body String query);
    }
}

class ServerResponse {

    /**
     * q1 : {"size":4,"data":[{"KOD":"00000","NIP":"","NAZ":"Kontrahent jednorazowy","_":0},{"KOD":"00001","NIP":"1111111111111","NAZ":"Dostawca 1","_":1},{"KOD":"00002","NIP":"2222222222222","NAZ":"Dostawca 2","_":2},{"KOD":"00003","NIP":"22222222711","NAZ":"macrologic SA","_":3}],"status":0}
     */

    private Q1Bean q1;

    public Q1Bean getQ1() {
        return q1;
    }

    public void setQ1(Q1Bean q1) {
        this.q1 = q1;
    }

    public static class Q1Bean {
        /**
         * size : 4
         * data : [{"KOD":"00000","NIP":"","NAZ":"Kontrahent jednorazowy","_":0},{"KOD":"00001","NIP":"1111111111111","NAZ":"Dostawca 1","_":1},{"KOD":"00002","NIP":"2222222222222","NAZ":"Dostawca 2","_":2},{"KOD":"00003","NIP":"22222222711","NAZ":"macrologic SA","_":3}]
         * status : 0
         */

        private int size;
        private int status;
        private List<Kontrahent> data;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Kontrahent> getData() {
            return data;
        }

        public void setData(List<Kontrahent> data) {
            this.data = data;
        }
    }
}