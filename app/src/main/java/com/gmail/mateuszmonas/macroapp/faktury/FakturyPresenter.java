package com.gmail.mateuszmonas.macroapp.faktury;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FakturyPresenter implements FakturyContract.Presenter {

    private DataRepository repository;
    private FakturyContract.View view;

    @Inject
    public FakturyPresenter(DataRepository repository, FakturyContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Inject
    void setupListener(){
        view.setPresenter(this);
    }

    public void start() {
        loadFaktury();
    }

    @Override
    public void loadFaktury() {
        List<Faktura> faktury = new ArrayList<>();
        faktury.add(new Faktura());
        faktury.add(new Faktura());
        faktury.add(new Faktura());
        view.showFaktury(faktury);
    }

    @Override
    public void openFakturaDetails() {
        view.showFakturaDetails();
    }
}
