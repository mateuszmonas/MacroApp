package com.gmail.mateuszmonas.macroapp.faktury;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturyPresenterModule {

    FakturyContract.View view;
    String kontrahentReference;

    public FakturyPresenterModule(FakturyContract.View view, String kontrahentReference) {
        this.view = view;
        this.kontrahentReference = kontrahentReference;
    }

    @Provides
    FakturyContract.View getView() {
        return view;
    }

    @Provides
    public String getKontrahentReference() {
        return kontrahentReference;
    }
}
