package com.gmail.mateuszmonas.macroapp.data;

import java.util.List;

public interface DataSource {

    void getKontrahenci(CallbackServerResponse<List<Kontrahent>> callback, int offset, String nazwa);

    void getFaktury(CallbackServerResponse<List<Faktura>> callback, String kontrahentReference, int offset, String symbol);

    void getDetaleFaktury(CallbackServerResponse<DetaleFaktury> detaleFakturyCallback, CallbackServerResponse<List<PozycjaFaktury>> pozycjeFakturyCallback, String fakturaReference);

    interface CallbackServerResponse<T> {

        void onResponse(T response);

        void onFailure();

    }

}
