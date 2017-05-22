package com.gmail.mateuszmonas.macroapp.kontrahenci;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.List;

import javax.inject.Inject;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private final KontrahenciContract.View view;
    private final DataRepository repository;

    @Inject
    KontrahenciPresenter(DataRepository repository, KontrahenciContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadKontrachenci(0, "", false);
    }


    @Override
    public void loadKontrachenci(final int offset, String nazwa, boolean forceUpdate) {

        if (forceUpdate) {
            repository.refreschCache();
        }

        view.setLoadingView(true);

        repository.getKontrahenci(new DataSource.CallbackServerResponse<List<Kontrahent>>() {
            @Override
            public void onResponse(List<Kontrahent> response) {
                view.setLoadingView(false);
                view.showKontrachenci(response, offset == 0);
            }

            @Override
            public void onFailure() {
                view.setLoadingView(false);
                view.setBrakPolaczeniaView(true);
            }
        }, offset, nazwa);
    }

    @Override
    public void openFaktury(Kontrahent kontrahent) {
        String kontrahentReference = kontrahent.getREFERENCE();
        String kontrahentName = kontrahent.getNAZ();
        view.showFaktury(kontrahentReference, kontrahentName);
    }

    @Override
    public void setNazwa(String nazwa) {
        view.setNazwa(nazwa);
    }
}
