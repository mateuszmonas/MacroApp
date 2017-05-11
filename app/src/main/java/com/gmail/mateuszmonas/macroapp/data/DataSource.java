package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

import retrofit2.Callback;

public interface DataSource {

    void getKontrahenci(Callback<ServerResponseKontrahenci> callback);

    void getFaktury(Callback<ServerResponseFaktury> callback, String kontrahentReference);

}
