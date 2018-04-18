package com.cmtruong.udacity.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.adapters.FetchItemInteractorImpl;
import com.cmtruong.udacity.adapters.MovieAdapter;
import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.presenter.MainPresenter;
import com.cmtruong.udacity.presenter.MainPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * `
 * My main activity which displays the grid view with image for all movies
 *
 * @author davidetruong
 * @version 1.0
 */
public class MainActivity extends Activity implements MainView, AdapterView.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    MainPresenter mPresenter;

    @BindView(R.id.pb_grid)
    ProgressBar progressBar;

    @BindView(R.id.gv_movie)
    GridView gridView;

    List<Movie> moviesList;

    SharedPreferences sharedPreferences;

    String sortType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gridView.setOnItemClickListener(this);
        mPresenter = new MainPresenterImpl(this, new FetchItemInteractorImpl());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sortType = sharedPreferences.getString(getResources().getString(R.string.pref_sort_key), Config.POPULAR);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: begin here ...");
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        gridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Movie> movies) {
        gridView.setAdapter(new MovieAdapter(this, movies));
        moviesList = movies;
        Log.i(TAG, "setItems: checked");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Movie movie = moviesList.get(i);
        mPresenter.onItemClicked(i, movie);
    }

    @Override
    public void navigateToDetail(Movie movie) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(Config.MOVIE_INTENT_KEY, movie);
        startActivity(intent);
    }

    @Override
    public String setSortType() {
        return sortType;
    }

    @Override
    public void setItemFavorite(List<Movie> movies) {
        gridView.setAdapter(new MovieAdapter(this, movies));
        moviesList = movies;
        Log.d(TAG, "setItemFavorite: checked");
    }

    @Override
    public boolean checkFlagFavorite(Boolean isFav) {
        return isFav;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            onResume();
            return true;
        }

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.pref_sort_key))) {
            sortType = sharedPreferences.getString(getResources().getString(R.string.pref_sort_key), Config.POPULAR);
        }
    }

}
