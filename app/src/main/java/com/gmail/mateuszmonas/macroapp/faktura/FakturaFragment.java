package com.gmail.mateuszmonas.macroapp.faktura;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.mateuszmonas.macroapp.R;

public class FakturaFragment extends Fragment implements FakturaContract.View {

    FakturaContract.Presenter presenter;

    public FakturaFragment() {
        // Required empty public constructor
    }

    public static FakturaFragment newInstance() {
        FakturaFragment fragment = new FakturaFragment();
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
        return inflater.inflate(R.layout.fragment_faktura, container, false);
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
    public void setPresenter(FakturaContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
