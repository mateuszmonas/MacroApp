package com.gmail.mateuszmonas.macroapp.faktury;



import com.gmail.mateuszmonas.macroapp.data.DataRepository;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.data.remote.ServerResponseFaktury;

import java.util.ArrayList;
import java.util.List;

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
        loadFaktury();
    }

    @Override
    public void loadFaktury() {
        List<Faktura> faktury = new ArrayList<>();
        faktury.add(new Faktura());
        faktury.add(new Faktura());
        faktury.add(new Faktura());
        view.showFaktury(faktury);

        Callback<ServerResponseFaktury> callback = new Callback<ServerResponseFaktury>() {
            @Override
            public void onResponse(Call<ServerResponseFaktury> call, Response<ServerResponseFaktury> response) {
                view.showFaktury(response.body().getFaktury());
            }

            @Override
            public void onFailure(Call<ServerResponseFaktury> call, Throwable t) {
                view.showBrakPolaczenia();
            }
        };
        //repository.getFaktury(callback, kontrahentReference);
    }

    @Override
    public void openFakturaDetails() {
        view.showFakturaDetails();
    }
}
