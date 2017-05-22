package com.gmail.mateuszmonas.macroapp.kontrahenci;

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

public class KontrahenciActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    KontrahenciPresenter presenter;
    private String EXTRA_SEARCHED = "SEARCHED";
    private boolean searched = false;
    private SearchView searchView;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrahenci);

        ButterKnife.bind(this);
        toolbar.setTitle(R.string.kontrahenci);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            searched = savedInstanceState.getBoolean(EXTRA_SEARCHED);
        }

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
                .dataRepositoryComponent(
                        ((MacroApplication) getApplication()).getDataRepositoryComponent()
                ).kontrahenciPresenterModule(new KontrahenciPresenterModule(kontrahenciFragment))
                .build().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (searched || !searchView.isIconified()) {
            searched = false;
            presenter.setNazwa("");
            hideSerch();
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
                presenter.setNazwa(query);
                hideSerch();
                searched = true;
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
