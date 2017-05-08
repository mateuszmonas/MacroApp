package com.gmail.mateuszmonas.macroapp.data.remote;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class DataSourceModule {

    @Singleton
    @Provides
    @Remote
    DataSource provideRemoteDataSource(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient){
        return new RemoteDataSource(retrofit, gson, okHttpClient);
    }

}
