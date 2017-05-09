package com.gmail.mateuszmonas.macroapp.data;


import retrofit2.Callback;

public interface DataSource {

    void getKontrahenci(Callback<String> callback);

    //parse response data from json if retrofit does not parse it automatically
    <T> T parseJson(String json, Class<T> classOfT);

}
