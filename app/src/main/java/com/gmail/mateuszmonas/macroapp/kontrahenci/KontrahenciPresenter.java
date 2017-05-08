package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.annotation.NonNull;

import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View view;
    private List<Kontrahent> kontrachenci;

    @Inject
    KontrahenciPresenter(@NonNull KontrahenciContract.View view) {
        this.view = view;
        kontrachenci = new ArrayList<>();
        for(int i = 0 ; i<50; i++) {
            kontrachenci.add(new Kontrahent(6, "NOWAK", 1234563218, "JAN NOWAK", "katowice, ul. Korfantego 125A"));
        }
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
