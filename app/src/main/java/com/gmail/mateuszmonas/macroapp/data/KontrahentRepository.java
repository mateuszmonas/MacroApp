package com.gmail.mateuszmonas.macroapp.data;


import com.gmail.mateuszmonas.macroapp.data.remote.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KontrahentRepository implements DataSource {

    private final DataSource remoteDataSource;

    @Inject
    public KontrahentRepository(@Remote DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
