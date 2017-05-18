package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Callback;

@Singleton
public class DataRepository implements DataSource {

    private final DataSource remoteDataSource;
    private final List<Kontrahent> kontrahentCache = new ArrayList<>();
    private final List<Faktura> fakturaCache = new ArrayList<>();
    private final List<DetaleFaktury> detaleFakturyCache = new ArrayList<>();
    private boolean refreshData = true;

    @Inject
    public DataRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getKontrahenci(final ServerResponseCallback<List<Kontrahent>> callback, int offset, String nazwa) {
        if (!refreshData && !kontrahentCache.isEmpty()) {
            callback.onResponse(kontrahentCache);
        } else {
            kontrahentCache.clear();
            remoteDataSource.getKontrahenci(new ServerResponseCallback<List<Kontrahent>>() {
                @Override
                public void onResponse(List<Kontrahent> response) {
                    kontrahentCache.addAll(response);
                    callback.onResponse(response);
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            }, offset, nazwa);
            refreshData = false;
        }
    }

    @Override
    public void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference, int offset, String symbol) {
        remoteDataSource.getFaktury(callback, kontrahentReference, offset, symbol);
    }

    @Override
    public void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference) {
        remoteDataSource.getDetaleFaktury(detaleFakturyCallback, pozycjeFakturyCallback, fakturaReference);
    }

    @Override
    public void refreshData() {
        refreshData = true;
    }
}
