package com.gmail.mateuszmonas.macroapp.faktury;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturyPresenterModule {

    private final FakturyContract.View view;
    private final String kontrahentReference;

    FakturyPresenterModule(FakturyContract.View view, String kontrahentReference) {
        this.view = view;
        this.kontrahentReference = kontrahentReference;
    }

    @Provides
    FakturyContract.View getView() {
        return view;
    }

    @Provides
    String getKontrahentReference() {
        return kontrahentReference;
    }
}
