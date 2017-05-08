package com.gmail.mateuszmonas.macroapp.kontrahenci;

import dagger.Module;
import dagger.Provides;

@Module
public class KontrahenciPresenterModule {

    KontrahenciContract.View view;

    public KontrahenciPresenterModule(KontrahenciContract.View view) {
        this.view = view;
    }

    @Provides
    public KontrahenciContract.View getView() {
        return view;
    }
}
