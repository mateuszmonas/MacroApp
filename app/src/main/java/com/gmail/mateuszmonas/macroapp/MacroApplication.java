package com.gmail.mateuszmonas.macroapp;

import android.app.Application;

import com.gmail.mateuszmonas.macroapp.data.DaggerKontrahentRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.KontrahentRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.remote.DataSourceModule;
import com.gmail.mateuszmonas.macroapp.utils.NetModule;


public class MacroApplication extends Application {

    KontrahentRepositoryComponent kontrahentRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();



        kontrahentRepositoryComponent = DaggerKontrahentRepositoryComponent.builder()
                .netModule(new NetModule("http://89.25.160.36:8080/ProcExec/batch-query/"))
                .dataSourceModule(new DataSourceModule()).build();
    }

    public KontrahentRepositoryComponent getKontrahentRepositoryComponent(){
        return kontrahentRepositoryComponent;
    }
}
