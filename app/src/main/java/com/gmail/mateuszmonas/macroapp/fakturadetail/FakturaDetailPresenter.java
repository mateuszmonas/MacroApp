package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;

import javax.inject.Inject;

public class FakturaDetailPresenter implements FakturaDetailContract.Presenter {

    private final DataRepository repository;
    private final FakturaDetailContract.View view;

    @Inject
    FakturaDetailPresenter(DataRepository repository, FakturaDetailContract.View view){
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
