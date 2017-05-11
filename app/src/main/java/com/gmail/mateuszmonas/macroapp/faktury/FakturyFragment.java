package com.gmail.mateuszmonas.macroapp.faktury;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.fakturadetail.FakturaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakturyFragment extends Fragment implements FakturyContract.View {

    @BindView(R.id.fakturyRecyclerViewer)
    RecyclerView fakturyRecyclerViewer;
    FakturyAdapter adapter;
    Unbinder unbinder;
    FakturyContract.Presenter presenter;

    public FakturyFragment() {
        // Required empty public constructor
    }

    public static FakturyFragment newInstance() {
        FakturyFragment fragment = new FakturyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FakturyAdapter(new ArrayList<Faktura>(), fakturyListListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faktury, container, false);
        unbinder = ButterKnife.bind(this, view);

        fakturyRecyclerViewer.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fakturyRecyclerViewer.setAdapter(adapter);

        return view;
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
    public void setPresenter(FakturyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFaktury(List<Faktura> faktury) {
        adapter.replaceData(faktury);
    }

    @Override
    public void showFakturaDetails() {
        Intent intent = new Intent(getContext(), FakturaDetailActivity.class);
        startActivity(intent);
    }

    FakturyListListener fakturyListListener = new FakturyListListener() {
        @Override
        public void onFakturaClick() {
            showFakturaDetails();
        }
    };

    interface FakturyListListener{

        void onFakturaClick();

    }

}
