package com.gmail.mateuszmonas.macroapp.kontrahenci;

import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.KontrahentRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View view;
    private KontrahentRepository repository;
    private List<Kontrahent> kontrachenci;

    @Inject
    KontrahenciPresenter(KontrahentRepository repository, KontrahenciContract.View view) {
        this.repository = repository;
        this.view = view;
        kontrachenci = new ArrayList<>();
        repository.getKontrahenci();
    }

    @Inject
    void setupListeners(){
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadKontrachenci(kontrachenci);
    }

    @Override
    public void loadKontrachenci(List<Kontrahent> kontrachenci) {
        view.showKontrachenci(kontrachenci);
    }

    @Override
    public void openFaktura(int id) {
        view.showFaktura(id);
    }
}
