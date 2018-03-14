package com.cmtruong.udacity.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.adapters.FetchItemInteractorImpl;
import com.cmtruong.udacity.adapters.MovieAdapter;
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
public class MainActivity extends Activity implements MainView, AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.pb_grid)
    ProgressBar progressBar;
    MainPresenter mPresenter;

    @BindView(R.id.gv_movie)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gridView.setOnItemClickListener(this);
        mPresenter = new MainPresenterImpl(this, new FetchItemInteractorImpl());
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
        Log.i(TAG, "setItems: checked");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.onItemClicked(i);
    }
}
