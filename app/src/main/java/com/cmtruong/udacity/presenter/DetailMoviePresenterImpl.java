package com.cmtruong.udacity.presenter;

import android.util.Log;

import com.cmtruong.udacity.adapters.FetchDetailInteractor;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Review;
import com.cmtruong.udacity.models.Video;
import com.cmtruong.udacity.views.DetailMovieView;

import java.util.List;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 9th, 2018
 */
public class DetailMoviePresenterImpl implements DetailMoviePresenter, FetchDetailInteractor.OnLoadListener {

    private static final String TAG = DetailMoviePresenterImpl.class.getSimpleName();
    private FetchDetailInteractor mInteractor;
    private DetailMovieView mView;

    public DetailMoviePresenterImpl(FetchDetailInteractor mInteractor, DetailMovieView mView) {
        this.mInteractor = mInteractor;
        this.mView = mView;
    }

    @Override
    public void onResumeTrailer() {

    }

    @Override
    public void onDestroyTrailer() {

    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: started");
        if (mView != null) mInteractor.fetchData(this);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onResumeReview() {

    }

    @Override
    public void onDestroyReview() {

    }

    @Override
    public void onVideoItemClicked() {

    }


    @Override
    public void onReviewFinished(List<Review> reviews) {
        if (mView != null) {
            Log.d(TAG, "onReviewFinished: " + reviews.toString());
            mView.setReviewItem(reviews);
        }
    }

    @Override
    public void onTrailerListener(List<Video> videos) {
        if (mView != null) {
            Log.d(TAG, "onTrailerListener: " + videos.toString());
            mView.setTrailerItem(videos);
        }
    }


    @Override
    public Movie getMovie() {
        return mView.setMovieItem();
    }
}
