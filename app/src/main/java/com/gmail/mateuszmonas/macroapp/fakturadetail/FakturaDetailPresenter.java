package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.DetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.List;

import javax.inject.Inject;

class FakturaDetailPresenter implements FakturaDetailContract.Presenter {

    private final DataRepository repository;
    private final FakturaDetailContract.View view;
    private final String fakturaReference;

    @Inject
    FakturaDetailPresenter(DataRepository repository, FakturaDetailContract.View view, String fakturaReference) {
        this.repository = repository;
        this.view = view;
        this.fakturaReference = fakturaReference;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadDetaleFaktury(false);
    }

    @Override
    public void loadDetaleFaktury(boolean forceUpdate) {

        if (forceUpdate) {
            repository.refreshFakturaDetailCache();
        }

        view.setLoadingView(true);

        repository.getDetaleFaktury(new DataSource.CallbackServerResponse<DetaleFaktury>() {
            @Override
            public void onResponse(DetaleFaktury response) {
                view.showDetaleFaktury(response);
                view.setBrakPolaczeniaView(false);
                view.setLoadingView(false);
            }

            @Override
            public void onFailure() {
                view.setBrakPolaczeniaView(true);
                view.setLoadingView(false);
            }
        }, new DataSource.CallbackServerResponse<List<PozycjaFaktury>>() {
            @Override
            public void onResponse(List<PozycjaFaktury> response) {
                view.showPozycjeFaktury(response);
                view.setBrakPolaczeniaView(false);
                view.setLoadingView(false);
            }

            @Override
            public void onFailure() {
                view.setBrakPolaczeniaView(true);
                view.setLoadingView(false);
            }
        }, fakturaReference);
    }
}
