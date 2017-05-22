package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.DetaleFaktury;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakturaDetailFragment extends Fragment implements FakturaDetailContract.View {

    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fakturaDetailRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nazwaNabywcy)
    TextView nazwaNabywcy;
    @BindView(R.id.adresNabywcy)
    TextView adresNabywcy;
    @BindView(R.id.nip)
    TextView nip;
    @BindView(R.id.suma)
    TextView suma;
    @BindView(R.id.nabywcaView)
    LinearLayout nabywcaView;
    private FakturaDetailContract.Presenter presenter;
    private FakturaDetailAdapter adapter;
    private Unbinder unbinder;

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
        adapter = new FakturaDetailAdapter(new ArrayList<PozycjaFaktury>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faktura_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadDetaleFaktury(true);
            }
        });

        return view;
    }

    @Override
    public void showPozycjeFaktury(List<PozycjaFaktury> pozycje) {
        adapter.replaceData(pozycje);
    }

    @Override
    public void showDetaleFaktury(DetaleFaktury detaleFaktury) {
        String adres = detaleFaktury.getKodPocztowy() + " " + detaleFaktury.getMiasto() + ", " + detaleFaktury.getUlica();
        String nipText = "NIP: " + detaleFaktury.getNip();
        String sumaText = "Razem do zapłaty: " + new DecimalFormat("0.00").format(detaleFaktury.getBrutto()) + "zł";
        nazwaNabywcy.setText(detaleFaktury.getNazwa());
        adresNabywcy.setText(adres);
        nip.setText(nipText);
        suma.setText(sumaText);
    }

    @Override
    public void setBrakPolaczeniaView(boolean visible) {
        if (getView() != null) {
            if (visible) {
                nabywcaView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                brakPolaczenia.setVisibility(View.VISIBLE);
            } else {
                nabywcaView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                brakPolaczenia.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setLoadingView(boolean visible) {
        if (getView() != null) {
            swipeRefreshLayout.setRefreshing(visible);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
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
