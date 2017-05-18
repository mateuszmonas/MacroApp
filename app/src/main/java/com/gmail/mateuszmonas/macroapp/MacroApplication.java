package com.gmail.mateuszmonas.macroapp;

import android.app.Application;
import android.os.StrictMode;

import com.gmail.mateuszmonas.macroapp.data.DaggerDataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.DataRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.remote.DataSourceModule;
import com.gmail.mateuszmonas.macroapp.utils.NetModule;


public class MacroApplication extends Application {

    private DataRepositoryComponent dataRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());

        dataRepositoryComponent = DaggerDataRepositoryComponent.builder()
                .netModule(new NetModule(getString(R.string.login), getString(R.string.password), getString(R.string.address)))
                .dataSourceModule(new DataSourceModule()).build();
    }

    public DataRepositoryComponent getDataRepositoryComponent() {
        return dataRepositoryComponent;
    }
}
