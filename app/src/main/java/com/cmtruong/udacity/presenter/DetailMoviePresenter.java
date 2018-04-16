package com.cmtruong.udacity.presenter;


/**
 * @author davidetruong
 * @version 1.0
 * @since April 9th, 2018
 */
public interface DetailMoviePresenter {

    void onResumeTrailer();

    void onDestroyTrailer();

    void onResume();


    void onDestroy();

    void onResumeReview();

    void onDestroyReview();

    void onVideoItemClicked();
}
