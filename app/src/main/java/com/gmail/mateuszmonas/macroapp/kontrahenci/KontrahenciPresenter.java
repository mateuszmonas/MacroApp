package com.gmail.mateuszmonas.macroapp.kontrahenci;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

import java.util.List;

import retrofit2.Callback;

import javax.inject.Inject;

import retrofit2.Call;

class KontrahenciPresenter implements KontrahenciContract.Presenter {

    private KontrahenciContract.View view;
    private DataRepository repository;

    @Inject
    KontrahenciPresenter(DataRepository repository, KontrahenciContract.View view) {
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

        view.showLoadingView();

            Callback<ServerResponseKontrahenci> callback = new retrofit2.Callback<ServerResponseKontrahenci>() {
                @Override
                public void onResponse(Call<ServerResponseKontrahenci> call, retrofit2.Response<ServerResponseKontrahenci> response) {
                    view.hideLoadingView();
                    List<Kontrahent> kontrahenci = response.body().getQ1().getData();
                    view.showKontrachenci(kontrahenci);
                }

                @Override
                public void onFailure(Call<ServerResponseKontrahenci> call, Throwable t) {
                    view.hideLoadingView();
                    view.showBrakPolaczenia();
                }
        };
        repository.getKontrahenci(callback);
    }

    @Override
    public void openFaktury(String kontrahentReference) {
        view.showFaktury(kontrahentReference);
    }
}
