package com.gmail.mateuszmonas.macroapp.data;

import com.gmail.mateuszmonas.macroapp.data.remote.DataSourceModule;
import com.gmail.mateuszmonas.macroapp.utils.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataSourceModule.class, NetModule.class})
public interface DataRepositoryComponent {

    DataRepository getDataRepository();

}
