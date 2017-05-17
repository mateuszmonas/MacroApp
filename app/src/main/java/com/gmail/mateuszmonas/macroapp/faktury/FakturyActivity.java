package com.gmail.mateuszmonas.macroapp.faktury;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.MacroApplication;
import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturyActivity extends AppCompatActivity {

    private static final String EXTRA_KONTRAHENT_REFERENCE = "KONTRAHENT_REFERENCE";
    private static final String EXTRA_KONTRAHENT_NAME = "KONTRAHENT_NAME";

    @Inject
    FakturyPresenter presenter;

    @BindView(R.id.title)
    TextView title;

    public static Intent createIntent(Context context, String kontrahentReference, String kontrahentName) {
        Intent intent = new Intent(context, FakturyActivity.class);
        intent.putExtra(EXTRA_KONTRAHENT_NAME, kontrahentName);
        intent.putExtra(EXTRA_KONTRAHENT_REFERENCE, kontrahentReference);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktury);

        ButterKnife.bind(this);

        title.setText(getIntent().getStringExtra(EXTRA_KONTRAHENT_NAME));

        String kontrahentReference = getIntent().getStringExtra(EXTRA_KONTRAHENT_REFERENCE);

        FakturyFragment fragment =
                (FakturyFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = FakturyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        DaggerFakturyComponent.builder().dataRepositoryComponent(((MacroApplication) getApplication()).getDataRepositoryComponent())
                .fakturyPresenterModule(new FakturyPresenterModule(fragment, kontrahentReference)).build().inject(this);
    }
}
