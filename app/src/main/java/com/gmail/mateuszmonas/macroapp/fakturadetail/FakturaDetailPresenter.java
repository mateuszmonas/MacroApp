package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseDetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        loadDetaleFaktury();
    }

    @Override
    public void loadDetaleFaktury() {

        view.setLoadingView(true);

        Callback<ServerResponseDetaleFaktury> detaleFakturyCallback = new Callback<ServerResponseDetaleFaktury>() {
            @Override
            public void onResponse(Call<ServerResponseDetaleFaktury> call, Response<ServerResponseDetaleFaktury> response) {
                view.showDetaleFaktury(response.body().getQ1().getData().get(0));
                view.setBrakPolaczeniaView(false);
                view.setLoadingView(false);
            }

            @Override
            public void onFailure(Call<ServerResponseDetaleFaktury> call, Throwable t) {
                view.setBrakPolaczeniaView(true);
                view.setLoadingView(false);
            }
        };

        Callback<ServerResponsePozycjeFaktury> pozycjeFakturyCallback = new Callback<ServerResponsePozycjeFaktury>() {
            @Override
            public void onResponse(Call<ServerResponsePozycjeFaktury> call, Response<ServerResponsePozycjeFaktury> response) {
                view.showPozycjeFaktury(response.body().getQ1().getData());
                view.setLoadingView(false);
            }

            @Override
            public void onFailure(Call<ServerResponsePozycjeFaktury> call, Throwable t) {
                t.printStackTrace();
                view.setBrakPolaczeniaView(true);
                view.setLoadingView(false);
            }
        };

        repository.getDetaleFaktury(detaleFakturyCallback, pozycjeFakturyCallback, fakturaReference);

    }
}
