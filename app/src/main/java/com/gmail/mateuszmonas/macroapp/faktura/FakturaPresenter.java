package com.gmail.mateuszmonas.macroapp.faktura;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;

import javax.inject.Inject;

public class FakturaPresenter implements FakturaContract.Presenter {

    private final DataRepository repository;
    private final FakturaContract.View view;

    @Inject
    FakturaPresenter(DataRepository repository, FakturaContract.View view){
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
