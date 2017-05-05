package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.mateuszmonas.macroapp.R;

public class KontrahenciFragment extends Fragment implements KontrahenciContract.View{

    private KontrahenciContract.Presenter presenter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kontrahenci, container, false);
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
    public void setPresenter(KontrahenciContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
