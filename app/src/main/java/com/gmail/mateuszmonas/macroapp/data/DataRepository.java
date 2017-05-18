package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

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
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback, int offset, String nazwa) {
        remoteDataSource.getKontrahenci(callback, offset, nazwa);
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference, int offset, String symbol) {
        remoteDataSource.getFaktury(callback, kontrahentReference, offset, symbol);
    }

    @Override
    public void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference) {
        remoteDataSource.getDetaleFaktury(detaleFakturyCallback, pozycjeFakturyCallback, fakturaReference);
    }
}
