package com.gmail.mateuszmonas.macroapp.faktura;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturaPresenterModule {

    private final FakturaContract.View view;

    public FakturaPresenterModule(FakturaContract.View view) {
        this.view = view;
    }

    @Provides
    public FakturaContract.View getView() {
        return view;
    }
}
