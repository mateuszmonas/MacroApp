package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Callback;

@Singleton
public class KontrahentRepository implements DataSource {

    private final DataSource remoteDataSource;

    @Inject
    public KontrahentRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getKontrahenci(Callback<String> callback) {
        remoteDataSource.getKontrahenci(callback);
    }

    @Override
    public <T> T parseJson(String json, Class<T> classOfT) {
        return remoteDataSource.parseJson(json, classOfT);
    }
}
