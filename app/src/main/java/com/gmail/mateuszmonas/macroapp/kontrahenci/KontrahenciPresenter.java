package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.annotation.NonNull;

import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.ArrayList;
import java.util.List;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View kontrahenciView;
    private List<Kontrahent> kontrachenci;

    KontrahenciPresenter(@NonNull KontrahenciContract.View kontrahenciView) {
        this.kontrahenciView = kontrahenciView;
        kontrahenciView.setPresenter(this);
        kontrachenci = new ArrayList<>();
        for(int i = 0 ; i<50; i++) {
            kontrachenci.add(new Kontrahent(6, "NOWAK", 1234563218, "JAN NOWAK", "katowice, ul. Korfantego 125A"));
        }
    }

    @Override
    public void start() {
        loadKontrachenci(kontrachenci);
    }

    @Override
    public void loadKontrachenci(List<Kontrahent> kontrachenci) {
        kontrahenciView.showKontrachenci(kontrachenci);
    }

    @Override
    public void openFaktura(int id) {
        kontrahenciView.showFaktura(id);
    }
}
