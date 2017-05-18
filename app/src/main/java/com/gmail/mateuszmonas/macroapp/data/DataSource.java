package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

import java.util.List;

import retrofit2.Callback;

public interface DataSource {

    void getKontrahenci(ServerResponseCallback<List<Kontrahent>> response, int offset, String nazwa);

    void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference, int offset, String symbol);

    void getDetaleFaktury(Callback<ServerResponseDetaleFaktury> detaleFakturyCallback, Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback, String fakturaReference);

    void refreshData();

    interface ServerResponseCallback<T> {

        void onResponse(T response);

        void onFailure();

    }

}
