package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;
import com.gmail.mateuszmonas.macroapp.R;

import javax.inject.Inject;

public class FakturaDetailActivity extends AppCompatActivity {

    @Inject
    FakturaDetailPresenter fakturaDetailPresenter;

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, FakturaDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktura_detail);

        FakturaDetailFragment fakturaDetailFragment
                = (FakturaDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fakturaDetailFragment == null){
            fakturaDetailFragment = FakturaDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fakturaDetailFragment, R.id.contentFrame);
        }


    }
}
