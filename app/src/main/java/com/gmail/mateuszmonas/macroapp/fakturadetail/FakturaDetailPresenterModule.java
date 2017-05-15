package com.gmail.mateuszmonas.macroapp.fakturadetail;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturaDetailPresenterModule {

    private final FakturaDetailContract.View view;

    private String fakturaReference;

    public FakturaDetailPresenterModule(FakturaDetailContract.View view, String fakturaReference) {
        this.view = view;
        this.fakturaReference = fakturaReference;
    }

    @Provides
    public FakturaDetailContract.View getView() {
        return view;
    }

    @Provides
    public String getFakturaReference() {
        return fakturaReference;
    }
}
