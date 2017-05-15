package com.gmail.mateuszmonas.macroapp.faktury;

import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FakturyPresenter implements FakturyContract.Presenter {

    private DataRepository repository;
    private FakturyContract.View view;
    private String kontrahentReference;

    @Inject
    public FakturyPresenter(DataRepository repository, FakturyContract.View view, String kontrahentReference) {
        this.repository = repository;
        this.view = view;
        this.kontrahentReference = kontrahentReference;
    }

    @Inject
    void setupListener(){
        view.setPresenter(this);
    }

    public void start() {
        loadFaktury(0);
    }

    @Override
    public void loadFaktury(int offset) {

        view.showLoadingView();

        Callback<ServerResponseFaktury> callback = new Callback<ServerResponseFaktury>() {
            @Override
            public void onResponse(Call<ServerResponseFaktury> call, Response<ServerResponseFaktury> response) {
                view.hideLoadingView();
                view.showFaktury(response.body().getQ1().getData());
            }

            @Override
            public void onFailure(Call<ServerResponseFaktury> call, Throwable t) {
                t.printStackTrace();
                view.hideLoadingView();
                view.showBrakPolaczenia();
            }
        };
        repository.getFaktury(callback, kontrahentReference, offset);
    }

    @Override
    public void openFakturaDetails() {
        view.showFakturaDetails();
    }
}
