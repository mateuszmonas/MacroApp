package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

import java.util.List;

import retrofit2.Callback;

public interface DataSource {

    void getKontrahenci(CallbackServerResponse<List<Kontrahent>> callback, int offset, String nazwa);

    void getFaktury(CallbackServerResponse<List<Faktura>> callback, String kontrahentReference, int offset, String symbol);

    void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference);

    interface CallbackServerResponse<T> {

        void onResponse(T response);

        void onFailure();

    }

}
