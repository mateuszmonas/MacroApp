package com.gmail.mateuszmonas.macroapp.faktury;

import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

import javax.inject.Inject;

class FakturyPresenter implements FakturyContract.Presenter {

    private final DataRepository repository;
    private final FakturyContract.View view;
    private final String kontrahentReference;

    @Inject
    FakturyPresenter(DataRepository repository, FakturyContract.View view, String kontrahentReference) {
        this.repository = repository;
        this.view = view;
        this.kontrahentReference = kontrahentReference;
    }

    @Inject
    void setupListener() {
        view.setPresenter(this);
    }

    public void start() {
        loadFaktury(0, "", false);
    }

    @Override
    public void loadFaktury(final int offset, String symbol, boolean forceUpdate) {

        if (forceUpdate) {
            repository.refreschFakturaCache();
        }

        view.setLoadingView(true);

        repository.getFaktury(new DataSource.CallbackServerResponse<List<Faktura>>() {
            @Override
            public void onResponse(List<Faktura> response) {
                view.setLoadingView(false);
                view.showFaktury(response, offset == 0);
            }

            @Override
            public void onFailure() {
                view.setLoadingView(false);
                view.setBrakPolaczeniaView(true);
            }
        }, kontrahentReference, offset, symbol);
    }

    @Override
    public void openFakturaDetails(String fakturaReference) {
        view.showFakturaDetails(fakturaReference);
    }

    @Override
    public void setSymbol(String symbol) {
        view.setSymbol(symbol);
    }
}
