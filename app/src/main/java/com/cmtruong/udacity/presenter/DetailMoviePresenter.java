package com.cmtruong.udacity.presenter;


import com.cmtruong.udacity.models.Movie;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 9th, 2018
 */
public interface DetailMoviePresenter {

    void onResumeTrailer();

    void onDestroyTrailer();

    void onResume();

    void insertToFavoriteList(Movie movie);

    void onDestroy();

    void onResumeReview();

    void onDestroyReview();

}
