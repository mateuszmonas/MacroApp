package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.faktura.FakturaActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KontrahenciFragment extends Fragment implements KontrahenciContract.View{


    private KontrahenciContract.Presenter presenter;
    private KontrahenciAdapter adapter;
    @BindView(R.id.kontrahenciRecyclerView) RecyclerView kontrachenciRecyclerView;
    Unbinder unbinder;

    public KontrahenciFragment() {
        // Required empty public constructor
    }

    public static KontrahenciFragment newInstance() {
        KontrahenciFragment fragment = new KontrahenciFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new KontrahenciAdapter(new ArrayList<Kontrahent>(0), kontrahenciListListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontrahenci, container, false);
        unbinder = ButterKnife.bind(this, view);
        kontrachenciRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        kontrachenciRecyclerView.setAdapter(adapter);
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
    public void setPresenter(KontrahenciContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showKontrachenci(List<Kontrahent> kontrachenci) {
        adapter.replaceData(kontrachenci);
    }

    @Override
    public void showFaktura(int id) {
        Intent intent = new Intent(getContext(), FakturaActivity.class);
        startActivity(intent);
    }

    KontrahenciListListener kontrahenciListListener = new KontrahenciListListener() {
        @Override
        public void onKontrachenClick(int id) {
            presenter.openFaktura(id);
        }
    };

    public interface KontrahenciListListener{

        void onKontrachenClick(int id);

    }

}
