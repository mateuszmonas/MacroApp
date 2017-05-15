package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;

import javax.inject.Inject;

public class FakturaDetailPresenter implements FakturaDetailContract.Presenter {

    private final DataRepository repository;
    private final FakturaDetailContract.View view;
    private final String fakturaReference;

    @Inject
    FakturaDetailPresenter(DataRepository repository, FakturaDetailContract.View view, String fakturaReference){
        this.repository = repository;
        this.view=view;
        this.fakturaReference = fakturaReference;
    }

    @Inject
    void setupListeners(){
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFakturaDetails() {

    }
}
