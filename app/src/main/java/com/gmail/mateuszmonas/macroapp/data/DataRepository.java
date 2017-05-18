package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
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
        if (!refreshData && !kontrahentCache.isEmpty() && offset + 9 <= kontrahentCache.size()) {
            callback.onResponse(kontrahentCache.subList(offset - 1, offset + 9));
        } else {
            remoteDataSource.getKontrahenci(new ServerResponseCallback<List<Kontrahent>>() {
                @Override
                public void onResponse(List<Kontrahent> response) {
                    kontrahentCache.clear();
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
    public void getFaktury(final ServerResponseCallback<List<Faktura>> callback, String kontrahentReference, int offset, String symbol) {
        if (!refreshData && !fakturaCache.isEmpty() && offset + 9 <= fakturaCache.size()) {
            callback.onResponse(fakturaCache.subList(offset - 1, offset + 9));
        } else {
            fakturaCache.clear();
            remoteDataSource.getFaktury(new ServerResponseCallback<List<Faktura>>() {
                @Override
                public void onResponse(List<Faktura> response) {
                    fakturaCache.addAll(response);
                    callback.onResponse(response);
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            }, kontrahentReference, offset, symbol);
            refreshData = false;
        }
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
