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
    private boolean cacheDirty = true;

    @Inject
    public DataRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getKontrahenci(final CallbackServerResponse<List<Kontrahent>> callback, final int offset, String nazwa) {
        if (!cacheDirty && !kontrahentCache.isEmpty() && (kontrahentCache.size() >= offset + 10 || kontrahentCache.size() % 10 != 0)) {
            if (kontrahentCache.size() % 10 != 0 && kontrahentCache.size() < offset + 10) {
                callback.onResponse(kontrahentCache.subList(offset, kontrahentCache.size()));
            } else {
                callback.onResponse(kontrahentCache.subList(offset, offset + 10));
            }
        } else {
            remoteDataSource.getKontrahenci(new CallbackServerResponse<List<Kontrahent>>() {
                @Override
                public void onResponse(List<Kontrahent> response) {
                    if (cacheDirty) {
                        kontrahentCache.clear();
                    }
                    kontrahentCache.addAll(response);
                    callback.onResponse(response);
                    cacheDirty = false;
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            }, offset, nazwa);
        }
    }

    @Override
    public void getFaktury(CallbackServerResponse<List<Faktura>> callback, String kontrahentReference, int offset, String symbol) {
        remoteDataSource.getFaktury(callback, kontrahentReference, offset, symbol);
    }

    @Override
    public void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference) {
        remoteDataSource.getDetaleFaktury(detaleFakturyCallback, pozycjeFakturyCallback, fakturaReference);
    }

    public void refreschCache() {
        cacheDirty = true;
    }
}
