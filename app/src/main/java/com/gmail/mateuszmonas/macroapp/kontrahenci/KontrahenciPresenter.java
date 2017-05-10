package com.gmail.mateuszmonas.macroapp.kontrahenci;


import com.gmail.mateuszmonas.macroapp.data.KontrahentRepository;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

import retrofit2.Callback;

import javax.inject.Inject;

import retrofit2.Call;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View view;
    private KontrahentRepository repository;

    @Inject
    KontrahenciPresenter(KontrahentRepository repository, KontrahenciContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Inject
    void setupListeners(){
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadKontrachenci();
    }

    @Override
    public void loadKontrachenci() {
            Callback<ServerResponseKontrahenci> callback = new retrofit2.Callback<ServerResponseKontrahenci>() {
                @Override
                public void onResponse(Call<ServerResponseKontrahenci> call, retrofit2.Response<ServerResponseKontrahenci> response) {
                    view.showKontrachenci(response.body().getQ1().getData());
                }

                @Override
                public void onFailure(Call<ServerResponseKontrahenci> call, Throwable t) {
                }
        };
        repository.getKontrahenci(callback);
    }

    @Override
    public void openFaktura(int id) {
        view.showFaktura(id);
    }
}
