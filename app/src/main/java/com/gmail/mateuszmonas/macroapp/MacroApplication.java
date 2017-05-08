package com.gmail.mateuszmonas.macroapp;

import android.app.Application;

import com.gmail.mateuszmonas.macroapp.data.DaggerKontrahentRepositoryComponent;
import com.gmail.mateuszmonas.macroapp.data.KontrahentRepositoryComponent;


public class MacroApplication extends Application {

    KontrahentRepositoryComponent kontrahentRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        kontrahentRepositoryComponent = DaggerKontrahentRepositoryComponent.create();
    }

    public KontrahentRepositoryComponent getKontrahentRepositoryComponent(){
        return kontrahentRepositoryComponent;
    }
}
