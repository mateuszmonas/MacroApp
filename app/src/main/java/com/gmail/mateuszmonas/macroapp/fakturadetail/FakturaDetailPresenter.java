package com.gmail.mateuszmonas.macroapp.fakturadetail;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponsePozycjeFaktury;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        loadFakturaDetails();
    }

    @Override
    public void loadFakturaDetails() {

        Callback<ServerResponsePozycjeFaktury> callback =  new Callback<ServerResponsePozycjeFaktury>() {
            @Override
            public void onResponse(Call<ServerResponsePozycjeFaktury> call, Response<ServerResponsePozycjeFaktury> response) {
                view.showPozycjeFaktury(response.body().getQ1().getData());
            }

            @Override
            public void onFailure(Call<ServerResponsePozycjeFaktury> call, Throwable t) {

            }
        };
        repository.getPozycjeFaktury(callback, fakturaReference);
    }
}
