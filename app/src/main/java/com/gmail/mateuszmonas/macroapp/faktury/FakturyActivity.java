package com.gmail.mateuszmonas.macroapp.faktury;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String EXTRA_SEARCHED = "SEARCHED";
    private boolean searched = false;
    private SearchView searchView;
    private MenuItem menuItem;

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

        toolbar.setTitle(getIntent().getStringExtra(EXTRA_KONTRAHENT_NAME));
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            searched = savedInstanceState.getBoolean(EXTRA_SEARCHED);
        }

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

    @Override
    public void onBackPressed() {
        if (searched || !searchView.isIconified()) {
            searched = false;
            hideSerch();
            presenter.setSymbol("");
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        this.menuItem = menu.findItem(R.id.search);
        this.searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searched=true;
                hideSerch();
                presenter.setSymbol(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_SEARCHED, searched);
        super.onSaveInstanceState(outState);
    }

    private void hideSerch() {
        searchView.setQuery("", false);
        searchView.setIconified(true);
        menuItem.collapseActionView();
    }
}
