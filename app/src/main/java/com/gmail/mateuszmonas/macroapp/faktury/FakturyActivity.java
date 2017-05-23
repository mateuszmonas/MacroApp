package com.gmail.mateuszmonas.macroapp.faktury;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.mateuszmonas.macroapp.MacroApplication;
import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.fakturasearch.FakturaSearchActivity;
import com.gmail.mateuszmonas.macroapp.fakturasearch.FakturaSearchParameters;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturyActivity extends AppCompatActivity {

    public static final int SEARCH_REQUEST = 1;
    private static final String EXTRA_KONTRAHENT_REFERENCE = "KONTRAHENT_REFERENCE";
    private static final String EXTRA_KONTRAHENT_NAME = "KONTRAHENT_NAME";
    private static final String EXTRA_SEARCHED = "SEARCHED";
    private static final String EXTRA_SEARCH_PARAMETERS = "SEARCH_PARAMETERS";
    @Inject
    FakturyPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private boolean searched = false;
    private String kontrahentName;

    public static Intent createIntent(Context context, String kontrahentReference, String kontrahentName) {
        Intent intent = new Intent(context, FakturyActivity.class);
        intent.putExtra(EXTRA_KONTRAHENT_NAME, kontrahentName);
        intent.putExtra(EXTRA_KONTRAHENT_REFERENCE, kontrahentReference);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faktury);

        ButterKnife.bind(this);

        kontrahentName = getIntent().getStringExtra(EXTRA_KONTRAHENT_NAME);
        toolbar.setTitle(kontrahentName);
        setSupportActionBar(toolbar);

        String kontrahentReference = getIntent().getStringExtra(EXTRA_KONTRAHENT_REFERENCE);

        FakturyFragment fragment =
                (FakturyFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = FakturyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        DaggerFakturyComponent.builder().dataRepositoryComponent(((MacroApplication) getApplication()).getDataRepositoryComponent())
                .fakturyPresenterModule(new FakturyPresenterModule(fragment, kontrahentReference)).build().inject(this);

        if (savedInstanceState != null) {
            searched = savedInstanceState.getBoolean(EXTRA_SEARCHED);
            FakturaSearchParameters searchParameters = savedInstanceState.getParcelable(EXTRA_SEARCH_PARAMETERS);
            presenter.setSearchParameters(searchParameters);
        }
    }

    @Override
    public void onBackPressed() {
        //if currently displays searched fakturas it goes back to displaying all of them
        if (searched) {
            searched = false;
            presenter.setSearchParameters(new FakturaSearchParameters());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = FakturaSearchActivity.createIntent(getApplicationContext(), kontrahentName);
            startActivityForResult(intent, SEARCH_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        menu.findItem(R.id.search).setActionView(null);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST) {
            if (resultCode == RESULT_OK) {
                FakturaSearchParameters searchParameters = data.getParcelableExtra(EXTRA_SEARCH_PARAMETERS);
                presenter.setSearchParameters(searchParameters);
                searched=true;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //save if currently displaying only searched facturas on orientation change
        outState.putBoolean(EXTRA_SEARCHED, searched);
        outState.putParcelable(EXTRA_SEARCH_PARAMETERS, presenter.getSearchParameters());
        super.onSaveInstanceState(outState);
    }
}
