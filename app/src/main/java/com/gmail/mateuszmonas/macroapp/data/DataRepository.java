package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataRepository implements DataSource {

    private final DataSource remoteDataSource;
    private final List<Kontrahent> kontrahentCache = new ArrayList<>();
    private final List<Faktura> fakturaCache = new ArrayList<>();
    private final DetaleFaktury detaleFakturyCache = new DetaleFaktury();
    private final List<PozycjaFaktury> pozycjeFakturyCache = new ArrayList<>();
    private boolean kontrahentCacheDirty = true;
    private String currentKontrachentReference = "";
    private boolean fakturaCacheDirty = true;
    private boolean fakturaDetailCacheDirty = true;
    private String currentFakturaReference = "";

    @Inject
    public DataRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getKontrahenci(final CallbackServerResponse<List<Kontrahent>> callback, final int offset, String nazwa) {
        if (!kontrahentCacheDirty && !kontrahentCache.isEmpty() && (kontrahentCache.size() >= offset + 10 || kontrahentCache.size() % 10 != 0)) {
            if (kontrahentCache.size() % 10 != 0 && kontrahentCache.size() < offset + 10) {
                callback.onResponse(kontrahentCache.subList(offset, kontrahentCache.size()));
            } else {
                callback.onResponse(kontrahentCache.subList(offset, offset + 10));
            }
        } else {
            remoteDataSource.getKontrahenci(new CallbackServerResponse<List<Kontrahent>>() {
                @Override
                public void onResponse(List<Kontrahent> response) {
                    if (kontrahentCacheDirty) {
                        kontrahentCache.clear();
                    }
                    kontrahentCache.addAll(response);
                    callback.onResponse(response);
                    kontrahentCacheDirty = false;
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            }, offset, nazwa);
        }
    }

    @Override
    public void getFaktury(final CallbackServerResponse<List<Faktura>> callback, final String kontrahentReference, int offset, String symbol) {
        if (!fakturaCacheDirty && !fakturaCache.isEmpty() && (fakturaCache.size() >= offset + 10 || fakturaCache.size() % 10 != 0) && currentKontrachentReference.equals(kontrahentReference)) {
            if (fakturaCache.size() % 10 != 0 && fakturaCache.size() < offset + 10) {
                callback.onResponse(fakturaCache.subList(offset, fakturaCache.size()));
            } else {
                callback.onResponse(fakturaCache.subList(offset, offset + 10));
            }
        } else {
            remoteDataSource.getFaktury(new CallbackServerResponse<List<Faktura>>() {
                @Override
                public void onResponse(List<Faktura> response) {
                    if (fakturaCacheDirty || !currentKontrachentReference.equals(kontrahentReference)) {
                        fakturaCache.clear();
                    }
                    fakturaCache.addAll(response);
                    callback.onResponse(response);
                    fakturaCacheDirty = false;
                    currentKontrachentReference = kontrahentReference;
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            }, kontrahentReference, offset, symbol);
        }
    }

    @Override
    public void getDetaleFaktury(final CallbackServerResponse<DetaleFaktury> detaleFakturyCallback, final CallbackServerResponse<List<PozycjaFaktury>> pozycjeFakturyCallback, final String fakturaReference) {
        if (!fakturaDetailCacheDirty && currentFakturaReference.equals(fakturaReference)) {
            detaleFakturyCallback.onResponse(detaleFakturyCache);
            pozycjeFakturyCallback.onResponse(pozycjeFakturyCache);
        } else {
            remoteDataSource.getDetaleFaktury(new CallbackServerResponse<DetaleFaktury>() {
                @Override
                public void onResponse(DetaleFaktury response) {
                    detaleFakturyCache.setDetaleFaktury(response);
                    detaleFakturyCallback.onResponse(response);
                    currentFakturaReference = fakturaReference;
                    fakturaDetailCacheDirty = false;
                }

                @Override
                public void onFailure() {
                    detaleFakturyCallback.onFailure();
                }
            }, new CallbackServerResponse<List<PozycjaFaktury>>() {
                @Override
                public void onResponse(List<PozycjaFaktury> response) {
                    pozycjeFakturyCache.clear();
                    pozycjeFakturyCache.addAll(response);
                    pozycjeFakturyCallback.onResponse(response);
                }

                @Override
                public void onFailure() {
                    pozycjeFakturyCallback.onFailure();
                }
            }, fakturaReference);
        }
    }

    public void refreshKontrahentCache() {
        kontrahentCacheDirty = true;
    }

    public void refreshFakturaCache() {
        fakturaCacheDirty = true;
    }

    public void refreshFakturaDetailCache() {
        fakturaDetailCacheDirty = true;
    }
}
