package com.gmail.mateuszmonas.macroapp.fakturadetail;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturaDetailPresenterModule {

    private final FakturaDetailContract.View view;

    public FakturaDetailPresenterModule(FakturaDetailContract.View view) {
        this.view = view;
    }

    @Provides
    public FakturaDetailContract.View getView() {
        return view;
    }
}
