package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.mateuszmonas.macroapp.ActivityUtils;
import com.gmail.mateuszmonas.macroapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KontrahenciActivity extends AppCompatActivity {

    private KontrahenciPresenter kontrahenciPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrahenci);

        ButterKnife.bind(this);

        KontrahenciFragment kontrahenciFragment =
                (KontrahenciFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (kontrahenciFragment == null) {
            // Create the fragment
            kontrahenciFragment = KontrahenciFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), kontrahenciFragment, R.id.contentFrame);
        }

        // Create the presenter
        kontrahenciPresenter = new KontrahenciPresenter(kontrahenciFragment);

    }
}
