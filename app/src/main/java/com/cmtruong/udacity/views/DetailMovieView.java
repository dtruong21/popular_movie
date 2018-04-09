package com.cmtruong.udacity.views;

import com.cmtruong.udacity.models.Movie;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 9th, 2018
 */
public interface DetailMovieView {

    void showProgress();

    void hideProgress();

    void navigateToDetail(Movie movie);
}
