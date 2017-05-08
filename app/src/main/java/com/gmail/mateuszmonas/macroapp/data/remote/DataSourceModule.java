package com.gmail.mateuszmonas.macroapp.data.remote;

import com.gmail.mateuszmonas.macroapp.data.DataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    @Singleton
    @Provides
    @Remote
    public DataSource provideRemoteDataSource(){
        return new RemoteDataSource();
    }

}
