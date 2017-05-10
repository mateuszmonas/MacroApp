package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;
import com.gmail.mateuszmonas.macroapp.R;

import javax.inject.Inject;

public class FakturaDetailActivity extends AppCompatActivity {

    @Inject
    FakturaDetailPresenter fakturaDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktura);

        FakturaDetailFragment fakturaDetailFragment
                = (FakturaDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fakturaDetailFragment == null){
            fakturaDetailFragment = FakturaDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fakturaDetailFragment, R.id.contentFrame);
        }


    }
}
