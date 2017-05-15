package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.util.List;

import butterknife.BindView;

public class FakturaDetailFragment extends Fragment implements FakturaDetailContract.View {

    FakturaDetailContract.Presenter presenter;
    @BindView(R.id.brakPolaczenia)
    TextView brakPolaczenia;
    @BindView(R.id.loader)
    ProgressBar loader;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faktura_detail, container, false);
    }


    @Override
    public void showFakturaDetails(List<PozycjaFaktury> pozycje) {

    }
    @Override
    public void showBrakPolaczenia() {
        if(getView() != null) {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(FakturaDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
