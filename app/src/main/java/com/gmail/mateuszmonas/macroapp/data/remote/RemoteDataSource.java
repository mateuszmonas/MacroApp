package com.gmail.mateuszmonas.macroapp.data.remote;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class RemoteDataSource implements DataSource {

    private Retrofit retrofit;
    private Gson gson;

    @Inject
    RemoteDataSource(Retrofit retrofit, Gson gson) {
        this.retrofit = retrofit;
        this.gson = gson;
    }
}
