package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.faktury.FakturyActivity;
import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@FragmentScope
public class KontrahenciFragment extends Fragment implements KontrahenciContract.View{


    private KontrahenciContract.Presenter presenter;
    private KontrahenciAdapter adapter;
    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.brakKontrahentow)
    TextView brakKontrahentow;
    @BindView(R.id.kontrahenciRecyclerView) RecyclerView kontrachenciRecyclerView;
    Unbinder unbinder;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 10;
    int firstVisibleItem, visibleItemCount, totalItemCount;

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
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        kontrachenciRecyclerView.setLayoutManager(layoutManager);
        kontrachenciRecyclerView.setAdapter(adapter);
        kontrachenciRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = kontrachenciRecyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if(loading) {
                    if(totalItemCount > previousTotal){
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){

                    presenter.loadKontrachenci(adapter.getItemCount());

                    loading = true;

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
        presenter.loadKontrachenci(adapter.getItemCount());
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
        hideBrakPolaczenia();
        if (adapter.getItemCount()==0){
            showBrakKontrahentowView();
        } else {
            hideBrakKontrahentowView();
        }
    }

    @Override
    public void showFaktury(String kontrahentReference, String kontrahentName) {
        Intent intent = FakturyActivity.createIntent(getContext(), kontrahentReference, kontrahentName);
        startActivity(intent);
    }

    @Override
    public void showBrakKontrahentowView() {
        if(getView()!=null && adapter.getItemCount()==0)
        brakKontrahentow.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBrakKontrahentowView() {
        if(getView()!=null && adapter.getItemCount()!=0)
        brakKontrahentow.setVisibility(View.GONE);
    }

    @Override
    public void showBrakPolaczenia() {
        if(getView() != null && adapter.getItemCount()==0) {
            hideBrakKontrahentowView();
            brakPolaczenia.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void hideBrakPolaczenia() {
        if(getView()!=null)
        brakPolaczenia.setVisibility(View.GONE);
    }

    KontrahenciListListener kontrahenciListListener = new KontrahenciListListener() {
        @Override
        public void onKontrachenClick(int id) {
            presenter.openFaktury(adapter.getKontrahent(id));
        }
    };

    public interface KontrahenciListListener{

        void onKontrachenClick(int kontrahentReference);

    }

}
