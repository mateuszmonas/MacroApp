package com.gmail.mateuszmonas.macroapp.kontrahenci;


import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseKontrahenci;

import java.util.List;

import retrofit2.Callback;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

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
        loadKontrachenci(0);
    }

    @Override
    public void loadKontrachenci(int offset) {

        view.setLoadingView(true);

        Callback<ServerResponseKontrahenci> callback = new Callback<ServerResponseKontrahenci>() {
            @Override
            public void onResponse(Call<ServerResponseKontrahenci> call, Response<ServerResponseKontrahenci> response) {
                view.setLoadingView(false);
                List<Kontrahent> kontrahenci = response.body().getQ1().getData();
                view.showKontrachenci(kontrahenci);
            }

            @Override
            public void onFailure(Call<ServerResponseKontrahenci> call, Throwable t) {
                view.setLoadingView(false);
                view.setBrakPolaczeniaView(true);
            }
        };
        repository.getKontrahenci(callback, offset);
    }

    @Override
    public void openFaktury(Kontrahent kontrahent) {
        String kontrahentReference = kontrahent.getREFERENCE();
        String kontrahentName = kontrahent.getNAZ();
        view.showFaktury(kontrahentReference, kontrahentName);
    }
}
