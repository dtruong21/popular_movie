package com.cmtruong.udacity.adapters;

import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Review;
import com.cmtruong.udacity.models.Video;

import java.util.List;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 15th, 2018
 */
public interface FetchDetailInteractor {

    interface OnLoadListener {
        void onReviewFinished(List<Review> reviews);

        void onTrailerListener(List<Video> videos);

        Movie getMovie();

        void onInsertSuccess();
    }

    void fetchData(OnLoadListener listener);

    void addFavorite(Movie trailer, OnLoadListener listener);
}
