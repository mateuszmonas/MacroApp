package com.gmail.mateuszmonas.macroapp.faktury;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.fakturadetail.FakturaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakturyFragment extends Fragment implements FakturyContract.View {

    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.brakFaktur)
    TextView brakFaktur;
    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.fakturyRecyclerViewer)
    RecyclerView fakturyRecyclerViewer;
    FakturyAdapter adapter;
    Unbinder unbinder;
    FakturyContract.Presenter presenter;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    int firstVisibleItem, visibleItemCount, totalItemCount;

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

        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        fakturyRecyclerViewer.setLayoutManager(layoutManager);
        fakturyRecyclerViewer.setAdapter(adapter);
        fakturyRecyclerViewer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    visibleItemCount = fakturyRecyclerViewer.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if(loading) {
                        if(totalItemCount>previousTotal){
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if(!loading && (totalItemCount - visibleItemCount) < (firstVisibleItem + visibleThreshold)){

                        presenter.loadFaktury(adapter.getItemCount());

                        loading = true;

                    }
                }
            }
        });

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
        presenter.loadFaktury(adapter.getItemCount());
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
        hideBrakPolaczenia();
        if (adapter.getItemCount()==0){
            showBrakFakturView();
        } else {
            hideBrakFakturView();
        }
    }

    @Override
    public void showFakturaDetails(String fakturaReference) {
        Intent intent = FakturaDetailActivity.createIntent(getContext(), fakturaReference);
        startActivity(intent);
    }

    @Override
    public void showBrakFakturView() {
        if(getView()!=null && adapter.getItemCount()==0)
        brakFaktur.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBrakFakturView() {
        if(getView()!=null && adapter.getItemCount()!=0)
        brakFaktur.setVisibility(View.GONE);
    }

    @Override
    public void showBrakPolaczenia() {
        if(getView()!=null && adapter.getItemCount()==0) {
            hideBrakFakturView();
            brakPolaczenia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideBrakPolaczenia() {
        if(getView()!=null)
        brakPolaczenia.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingView() {
        if(getView()!=null)
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        if(getView()!=null)
        loader.setVisibility(View.GONE);
    }

    FakturyListListener fakturyListListener = new FakturyListListener() {
        @Override
        public void onFakturaClick(String fakturaReference) {
            presenter.openFakturaDetails(fakturaReference);
        }
    };

    interface FakturyListListener{

        void onFakturaClick(String fakturaReference);

    }

}
