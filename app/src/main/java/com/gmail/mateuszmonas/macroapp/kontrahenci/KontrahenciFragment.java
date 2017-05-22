package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.faktury.FakturyActivity;
import com.gmail.mateuszmonas.macroapp.utils.FragmentScope;
import com.gmail.mateuszmonas.macroapp.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@FragmentScope
public class KontrahenciFragment extends Fragment implements KontrahenciContract.View {


    private final int visibleThreshold = 4;
    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.brakKontrahentow)
    TextView brakKontrahentow;
    @BindView(R.id.kontrahenciRecyclerView)
    RecyclerView kontrachenciRecyclerView;
    @BindView(R.id.swipeRefresh)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private String EXTRA_KONTRAHENT_NAME = "KONTRAHENT_NAME";
    private Unbinder unbinder;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private KontrahenciContract.Presenter presenter;
    private KontrahenciAdapter adapter;
    private final KontrahenciListListener kontrahenciListListener = new KontrahenciListListener() {
        @Override
        public void onKontrachenClick(int id) {
            presenter.openFaktury(adapter.getKontrahent(id));
        }
    };
    //nazwa szukanego kontrahenta
    private String nazwa = "";
    private int previousTotal = 0;
    private boolean loading = true;

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

                if (dy > 0) {
                    visibleItemCount = kontrachenciRecyclerView.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount) < (firstVisibleItem + visibleThreshold)) {
                        try {
                            presenter.loadKontrachenci(adapter.getItemCount(), nazwa, false);
                        } catch (IllegalStateException e) {
                            Log.w("RecyclerView", e.getLocalizedMessage());
                            previousTotal = 0;
                        }

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

        swipeRefreshLayout.setScrollUpChild(kontrachenciRecyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadKontrachenci(0, nazwa, true);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadKontrachenci(0, nazwa, false);
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
    public void showKontrachenci(List<Kontrahent> kontrachenci, boolean forceUpdate) {
        adapter.replaceData(kontrachenci, forceUpdate);
        setBrakPolaczeniaView(false);
        if (adapter.getItemCount() == 0) {
            setBrakKontrahentowView(true);
        } else {
            setBrakKontrahentowView(false);
        }
    }

    @Override
    public void showFaktury(String kontrahentReference, String kontrahentName) {
        Intent intent = FakturyActivity.createIntent(getContext(), kontrahentReference, kontrahentName);
        startActivity(intent);
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
        presenter.loadKontrachenci(0, this.nazwa, true);
    }

    @Override
    public void setBrakKontrahentowView(boolean visible) {
        if (getView() != null) {
            if (visible && adapter.getItemCount() == 0) {
                brakKontrahentow.setVisibility(View.VISIBLE);
            } else {
                brakKontrahentow.setVisibility(View.GONE);
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
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(EXTRA_KONTRAHENT_NAME, nazwa);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            nazwa = savedInstanceState.getString(EXTRA_KONTRAHENT_NAME);
        }
        super.onActivityCreated(savedInstanceState);
    }

    interface KontrahenciListListener {

        void onKontrachenClick(int kontrahentReference);

    }

}
