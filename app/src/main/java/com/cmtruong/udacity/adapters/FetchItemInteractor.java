package com.cmtruong.udacity.adapters;

import com.cmtruong.udacity.models.Movie;

import java.util.List;

/**
 * Created by davidetruong on 13/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public interface FetchItemInteractor {
    interface onFinishedListener {
        void onFinished(List<Movie> movies);
    }

    void fetchItem(onFinishedListener listener);
}
