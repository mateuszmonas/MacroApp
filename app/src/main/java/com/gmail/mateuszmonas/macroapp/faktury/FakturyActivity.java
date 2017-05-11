package com.gmail.mateuszmonas.macroapp.faktury;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gmail.mateuszmonas.macroapp.MacroApplication;
import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class FakturyActivity extends AppCompatActivity {

    private static final String EXTRA_KONTRAHENT_REFERENCE = "KONTRAHENT_ID";

    @Inject FakturyPresenter presenter;

    public static Intent createIntent(Context context, String kontrahentReference){
        Intent intent = new Intent(context, FakturyActivity.class);
        intent.putExtra(EXTRA_KONTRAHENT_REFERENCE, kontrahentReference);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktury);

        ButterKnife.bind(this);

        String kontrahentReference = getIntent().getStringExtra(EXTRA_KONTRAHENT_REFERENCE);

        FakturyFragment fragment =
                (FakturyFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment == null){
            fragment = FakturyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        DaggerFakturyComponent.builder().dataRepositoryComponent(((MacroApplication) getApplication()).getDataRepositoryComponent())
                .fakturyPresenterModule(new FakturyPresenterModule(fragment, kontrahentReference)).build().inject(this);
    }
}
