package com.cmtruong.udacity.views;

import com.cmtruong.udacity.models.Movie;

import java.util.List;

/**
 * Created by davidetruong on 12/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public interface MainView {
    void showProgress();

    void hideProgress();

    void setItems(List<Movie> movies);

    void navigateToDetail(Movie movie);
}
