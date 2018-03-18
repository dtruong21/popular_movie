package com.cmtruong.udacity.presenter;

import com.cmtruong.udacity.models.Movie;

/**
 * Created by davidetruong on 13/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public interface MainPresenter {
    void onResume();

    void onItemClicked(int position, Movie movie);

    void onDestroy();
}
