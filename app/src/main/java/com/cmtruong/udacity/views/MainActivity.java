package com.cmtruong.udacity.views;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.cmtruong.udacity.data.MovieContract;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.presenter.MainPresenter;
import com.cmtruong.udacity.presenter.MainPresenterImpl;

import java.util.ArrayList;
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
    private static final String STATE_KEY = "Main";
    private static final String ITEM_KEY = "Item";

    MainPresenter mPresenter;

    @BindView(R.id.pb_grid)
    ProgressBar progressBar;

    @BindView(R.id.gv_movie)
    GridView gridView;

    private List<Movie> moviesList;

    SharedPreferences sharedPreferences;

    private String sortType;

    Parcelable state;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gridView.setOnItemClickListener(this);
        state = gridView.onSaveInstanceState();
        mPresenter = new MainPresenterImpl(this, new FetchItemInteractorImpl());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sortType = sharedPreferences.getString(getResources().getString(R.string.pref_sort_key), Config.POPULAR);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
        if (savedInstanceState != null && savedInstanceState.containsKey(ITEM_KEY)) {
            Log.d(TAG, "onCreate: - Restore data: " + savedInstanceState.getParcelableArrayList(ITEM_KEY));
            hideProgress();
            setItems(savedInstanceState.<Movie>getParcelableArrayList(ITEM_KEY));
        } else {
            mPresenter.onResume();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_KEY, state);
        outState.putParcelableArrayList(ITEM_KEY, (ArrayList<Movie>) moviesList);
        super.onSaveInstanceState(outState);
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
        moviesList = movies;
        gridView.setAdapter(new MovieAdapter(this, movies));
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

        if (id == R.id.action_favorite) {
            setItems(getAll());
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

    @Override
    public List<Movie> getAll() {
        List<Movie> movieDataList = new ArrayList<>();
        try {
            Cursor mCursor = getApplicationContext().getContentResolver().query
                    (MovieContract.MovieEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
            // Check that the cursor isn't null and contains an item.
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    movieDataList.add(getMovieData(mCursor));
                } while (mCursor.moveToNext());

            } else
                // Query was empty or returned null.
                movieDataList = null;

            if (mCursor != null && !mCursor.isClosed())
                mCursor.close();

        } catch (Exception ex) {
            return null;
        }

        return movieDataList;
    }


    private Movie getMovieData(Cursor data) {
        if (data == null)
            return null;
        else {
            int id = data.getInt(data.getColumnIndex(MovieContract.MovieEntry.COL_ID));
            String title = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_TITLE));
            String overview = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_OVERVIEW));
            String posterPath = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_POSTER));
            String releaseDate = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_DATE));
            String originalTitle = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_ORI_TITLE));
            String orignalLanguage = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_LANGUAGE));
            String backdropPath = data.getString(data.getColumnIndex(MovieContract.MovieEntry.COL_BACKDROP));
            Double vote = data.getDouble(data.getColumnIndex(MovieContract.MovieEntry.COL_VOTE));

            // Return a movie object.
            Movie movie = new Movie();
            movie.setId(id);
            movie.setBackdrop_path(backdropPath);
            movie.setOriginal_language(orignalLanguage);
            movie.setOriginal_title(originalTitle);
            movie.setOverview(overview);
            movie.setPoster_path(posterPath);
            movie.setTitle(title);
            movie.setRelease_date(releaseDate);
            movie.setVote_average(vote);
            return movie;
        }
    }


}
