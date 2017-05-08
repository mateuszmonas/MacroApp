package com.gmail.mateuszmonas.macroapp.faktura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;
import com.gmail.mateuszmonas.macroapp.R;

public class FakturaActivity extends AppCompatActivity {

    FakturaPresenter fakturaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktura);

        FakturaFragment fakturaFragment
                = (FakturaFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fakturaFragment == null){
            fakturaFragment = FakturaFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fakturaFragment, R.id.contentFrame);
        }

        fakturaPresenter = new FakturaPresenter(fakturaFragment);
    }
}
