package com.gmail.mateuszmonas.macroapp.kontrahenci;


import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.KontrahentRepository;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponse;

import retrofit2.Callback;

import java.util.ArrayList;
import java.util.List;

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
            Callback<String> callback = new retrofit2.Callback<String>() {
                @Override
                public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                    //for some reason retrofit does not parse this specific response by itself
                    //so i have to do it manually
                    ServerResponse serverResponse = repository.parseJson(response.body(), ServerResponse.class);
                    view.showKontrachenci(serverResponse.getQ1().getData());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
        };
        repository.getKontrahenci(callback);
    }

    @Override
    public void openFaktura(int id) {
        view.showFaktura(id);
    }
}
