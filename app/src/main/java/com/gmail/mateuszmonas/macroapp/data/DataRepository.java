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
    public void getKontrahenci(Callback<ServerResponseKontrahenci> callback, int offset) {
        remoteDataSource.getKontrahenci(callback, offset);
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference, int offset) {
        remoteDataSource.getFaktury(callback, kontrahentReference, offset);
    }

    @Override
    public void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference) {
        remoteDataSource.getDetaleFaktury(detaleFakturyCallback, pozycjeFakturyCallback, fakturaReference);
    }

    @Override
    public void searchKontrahenci(Callback<ServerResponseKontrahenci> callback, int offset, String nazwa) {
        remoteDataSource.searchKontrahenci(callback, offset, nazwa);
    }
}
