package com.gmail.mateuszmonas.macroapp.faktury;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Faktura;
import com.gmail.mateuszmonas.macroapp.fakturadetail.FakturaDetailActivity;
import com.gmail.mateuszmonas.macroapp.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FakturyFragment extends Fragment implements FakturyContract.View {

    private final int visibleThreshold = 3;
    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.brakFaktur)
    TextView brakFaktur;
    @BindView(R.id.fakturyRecyclerViewer)
    RecyclerView fakturyRecyclerViewer;
    @BindView(R.id.swipeRefresh)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private FakturyAdapter adapter;
    private Unbinder unbinder;
    private FakturyContract.Presenter presenter;
    private final FakturyListListener fakturyListListener = new FakturyListListener() {
        @Override
        public void onFakturaClick(String fakturaReference) {
            presenter.openFakturaDetails(fakturaReference);
        }
    };
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private String symbol = "";

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

                if (dy > 0) {
                    visibleItemCount = fakturyRecyclerViewer.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount) < (firstVisibleItem + visibleThreshold)) {

                        presenter.loadFaktury(adapter.getItemCount(), symbol, false);

                        loading = true;

                    }
                }
            }
        });

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(fakturyRecyclerViewer);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadFaktury(0, symbol, true);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadFaktury(adapter.getItemCount(), symbol, false);
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
    public void showFaktury(List<Faktura> faktury, boolean forceUpdate) {
        adapter.replaceData(faktury, forceUpdate);
        setBrakPolaczeniaView(false);
        if (adapter.getItemCount() == 0) {
            setBrakFakturView(true);
        } else {
            setBrakFakturView(false);
        }
    }

    @Override
    public void showFakturaDetails(String fakturaReference) {
        Intent intent = FakturaDetailActivity.createIntent(getContext(), fakturaReference);
        startActivity(intent);
    }

    @Override
    public void setBrakFakturView(boolean visible) {
        if (getView() != null) {
            if (visible && adapter.getItemCount() == 0) {
                brakFaktur.setVisibility(View.VISIBLE);
            } else {
                brakFaktur.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setBrakPolaczeniaView(boolean visible) {
        if (getView() != null) {
            if (visible && adapter.getItemCount() == 0) {
                brakPolaczenia.setVisibility(View.VISIBLE);
            } else {
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
    public void setSymbol(String symbol) {
        this.symbol = symbol;
        presenter.loadFaktury(0, this.symbol, true);
    }

    interface FakturyListListener {

        void onFakturaClick(String fakturaReference);

    }
}
