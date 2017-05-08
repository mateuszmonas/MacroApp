package com.gmail.mateuszmonas.macroapp.faktura;


import com.gmail.mateuszmonas.macroapp.data.KontrahentRepository;

import javax.inject.Inject;

public class FakturaPresenter implements FakturaContract.Presenter {

    private final KontrahentRepository repository;
    private final FakturaContract.View view;

    @Inject
    FakturaPresenter(KontrahentRepository repository, FakturaContract.View view){
        this.repository = repository;
        this.view=view;
    }

    @Inject
    void setupListeners(){
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
