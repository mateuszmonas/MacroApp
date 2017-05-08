package com.gmail.mateuszmonas.macroapp.data;

import com.gmail.mateuszmonas.macroapp.data.remote.DataSourceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DataSourceModule.class)
public interface KontrahentRepositoryComponent {

    KontrahentRepository getKontrahentRepository();

}
