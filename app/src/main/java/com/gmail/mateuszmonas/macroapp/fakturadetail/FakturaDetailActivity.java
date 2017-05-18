package com.gmail.mateuszmonas.macroapp.fakturadetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gmail.mateuszmonas.macroapp.MacroApplication;
import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturaDetailActivity extends AppCompatActivity {

    private static final String EXTRA_FAKTURA_REFERENCE = "FAKTURA_REFERENCE";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    FakturaDetailPresenter fakturaDetailPresenter;

    public static Intent createIntent(Context context, String fakturaReference) {
        Intent intent = new Intent(context, FakturaDetailActivity.class);
        intent.putExtra(EXTRA_FAKTURA_REFERENCE, fakturaReference);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktura_detail);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.detale_faktury);
        setSupportActionBar(toolbar);

        FakturaDetailFragment fakturaDetailFragment
                = (FakturaDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fakturaDetailFragment == null) {
            fakturaDetailFragment = FakturaDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fakturaDetailFragment, R.id.contentFrame);
        }

        String fakturaReference = getIntent().getStringExtra(EXTRA_FAKTURA_REFERENCE);

        DaggerFakturaDetailComponent.builder().dataRepositoryComponent(((MacroApplication) getApplication()).getDataRepositoryComponent())
                .fakturaDetailPresenterModule(new FakturaDetailPresenterModule(fakturaDetailFragment, fakturaReference)).build().inject(this);
    }
}
