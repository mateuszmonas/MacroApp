package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.mateuszmonas.macroapp.MacroApplication;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;
import com.gmail.mateuszmonas.macroapp.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class KontrahenciActivity extends AppCompatActivity {

    @Inject KontrahenciPresenter kontrahenciPresenter;

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
        DaggerKontrahenciComponent.builder()
                .kontrahentRepositoryComponent(
                        ((MacroApplication) getApplication()).getKontrahentRepositoryComponent()
                ).kontrahenciPresenterModule(new KontrahenciPresenterModule(kontrahenciFragment))
                .build().inject(this);

    }
}
