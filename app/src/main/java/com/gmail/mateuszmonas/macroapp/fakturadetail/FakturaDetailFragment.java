package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakturaDetailFragment extends Fragment implements FakturaDetailContract.View {

    FakturaDetailContract.Presenter presenter;
    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.fakturaDetailRecyclerView)
    RecyclerView recyclerView;
    FakturaDetailAdapter adapter;
    Unbinder unbinder;

    public FakturaDetailFragment() {
        // Required empty public constructor
    }

    public static FakturaDetailFragment newInstance() {
        FakturaDetailFragment fragment = new FakturaDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<PozycjaFaktury> a = new ArrayList<>();
        for(int i = 0 ; i<3 ; i++) {
            PozycjaFaktury pozycjaFaktury = new PozycjaFaktury();
            pozycjaFaktury.setIlosc(2);
            pozycjaFaktury.setCenaNetto(65.89);
            pozycjaFaktury.setJednostka("sztuka");
            pozycjaFaktury.setNazwa("Zestaw do kaniulacji dużych naczyń III-kanałowy 7F/15");
            pozycjaFaktury.setPozycja(i);
            pozycjaFaktury.setWartoscBrutto(142.32);
            pozycjaFaktury.setWartoscNetto(131.78);
            pozycjaFaktury.setWartoscVat(10.54);
            a.add(pozycjaFaktury);
        }
        adapter=new FakturaDetailAdapter(new ArrayList<PozycjaFaktury>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faktura_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void showPozycjeFaktury(List<PozycjaFaktury> pozycje) {
        adapter.replaceData(pozycje);
    }

    @Override
    public void showBrakPolaczenia() {
        if(getView() != null) {
            brakPolaczenia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingView() {
        if(getView()!=null) {
            loader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadingView() {
        if(getView()!=null) {
            loader.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideBrakPolaczenia() {
        if(getView()!=null) {
            brakPolaczenia.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(FakturaDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
