package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Callback;

@Singleton
public class DataRepository implements DataSource {

    private final DataSource remoteDataSource;

    @Inject
    public DataRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback) {
        remoteDataSource.getKontrahenci(callback);
    }


    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference) {
        remoteDataSource.getFaktury(callback, kontrahentReference);
    }
}
