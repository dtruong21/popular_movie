package com.cmtruong.udacity.presenter;

import android.util.Log;

import com.cmtruong.udacity.adapters.FetchItemInteractor;
import com.cmtruong.udacity.adapters.FetchItemInteractorImpl;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.views.MainView;

import java.util.List;

/**
 * Created by davidetruong on 13/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class MainPresenterImpl implements MainPresenter, FetchItemInteractorImpl.onFinishedListener {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainView mainView;
    private FetchItemInteractor interactor;

    public MainPresenterImpl(MainView mainView, FetchItemInteractor interactor) {
        this.mainView = mainView;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: check");
        if (mainView != null) {
            mainView.showProgress();
        }
        interactor.fetchItem(this);
        Log.i(TAG, "onResume: " + mainView.setSortType());
    }


    @Override
    public void onItemClicked(int position, Movie movie) {
        if (mainView != null) {
            mainView.navigateToDetail(movie);
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(List<Movie> movies) {
        if (mainView != null) {
            if (mainView.setSortType().equals("favorite")) {
                movies = mainView.getAll();
            }
            mainView.setItems(movies);
            mainView.hideProgress();
        }
    }

    @Override
    public String getSortType() {
        return mainView.setSortType();
    }
}
