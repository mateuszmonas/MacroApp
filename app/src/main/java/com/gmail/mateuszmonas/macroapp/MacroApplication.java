package com.gmail.mateuszmonas.macroapp;

import android.app.Application;

import com.gmail.mateuszmonas.macroapp.data.DaggerDataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.DataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.remote.DataSourceModule;
import com.gmail.mateuszmonas.macroapp.utils.NetModule;


public class MacroApplication extends Application {

    DataRepositoryComponent dataRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dataRepositoryComponent = DaggerDataRepositoryComponent.builder()
                .netModule(new NetModule("http://10.0.1.233:8080/"))
                .dataSourceModule(new DataSourceModule()).build();
    }

    public DataRepositoryComponent getDataRepositoryComponent(){
        return dataRepositoryComponent;
    }
}
