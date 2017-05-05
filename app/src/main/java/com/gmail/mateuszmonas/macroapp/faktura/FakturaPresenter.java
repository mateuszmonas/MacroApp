package com.gmail.mateuszmonas.macroapp.faktura;


public class FakturaPresenter implements FakturaContract.Presenter {

    FakturaContract.View view;

    FakturaPresenter(FakturaContract.View view){
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
