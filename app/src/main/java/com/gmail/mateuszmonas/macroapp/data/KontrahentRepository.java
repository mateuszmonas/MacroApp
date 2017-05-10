package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

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
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback) {
        remoteDataSource.getKontrahenci(callback);
    }


    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback) {

    }
}
