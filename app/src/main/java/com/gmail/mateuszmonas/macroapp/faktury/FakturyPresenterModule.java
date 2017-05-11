package com.gmail.mateuszmonas.macroapp.faktury;

import dagger.Module;
import dagger.Provides;

@Module
public class FakturyPresenterModule {

    FakturyContract.View view;

    public FakturyPresenterModule(FakturyContract.View view) {
        this.view = view;
    }

    @Provides
    FakturyContract.View getView(){
        return view;
    }
}
