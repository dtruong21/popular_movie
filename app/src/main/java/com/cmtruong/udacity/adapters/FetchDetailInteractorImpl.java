package com.cmtruong.udacity.adapters;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.cmtruong.udacity.api.MovieServices;
import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.data.MovieContract;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.ResultReview;
import com.cmtruong.udacity.models.ResultVideo;
import com.cmtruong.udacity.models.Review;
import com.cmtruong.udacity.models.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 15th, 2018
 */
public class FetchDetailInteractorImpl implements FetchDetailInteractor {

    private static final String TAG = FetchDetailInteractorImpl.class.getSimpleName();
    private List<Review> reviews = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    private Movie movie;

    @Override
    public void fetchData(final OnLoadListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + listener.getMovie());
                MovieServices movieServices = MovieServices.retrofit.create(MovieServices.class);
                movie = listener.getMovie();

                // Call first request
                Call<ResultReview> myReviews = movieServices.getReviews(movie.getId(), Config.API_KEY);
                myReviews.enqueue(new Callback<ResultReview>() {
                    @Override
                    public void onResponse(Call<ResultReview> call, Response<ResultReview> response) {
                        int statusCode = response.code();
                        Log.d(TAG, "onResponse call review: " + statusCode);

                        if (response.isSuccessful() && statusCode == 200) {
                            reviews = response.body().getReviews();
                            listener.onReviewFinished(reviews);
                            Log.d(TAG, "onResponse call review: " + reviews.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultReview> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage(), t);
                    }
                });
                // Call second request
                Call<ResultVideo> myVideos = movieServices.getVideos(movie.getId(), Config.API_KEY);
                Log.d(TAG, "run: " + myVideos.toString());
                myVideos.enqueue(new Callback<ResultVideo>() {
                    @Override
                    public void onResponse(Call<ResultVideo> call, Response<ResultVideo> response) {
                        int statusCode = response.code();
                        Log.d(TAG, "onResponse call video: " + statusCode);

                        if (response.isSuccessful() && statusCode == 200) {
                            videos = response.body().getVideos();
                            listener.onTrailerListener(videos);
                            Log.d(TAG, "onResponse call video: " + videos.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultVideo> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage(), t);
                    }
                });
            }
        }, 100);
    }

    @Override
    public void addFavorite(final Movie trailer, final OnLoadListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MovieContract.MovieEntry.COL_ID, trailer.getId());
                contentValues.put(MovieContract.MovieEntry.COL_TITLE, trailer.getTitle());
                contentValues.put(MovieContract.MovieEntry.COL_BACKDROP, trailer.getBackdrop_path());
                contentValues.put(MovieContract.MovieEntry.COL_DATE, trailer.getRelease_date());
                contentValues.put(MovieContract.MovieEntry.COL_LANGUAGE, trailer.getOriginal_language());
                contentValues.put(MovieContract.MovieEntry.COL_ORI_TITLE, trailer.getOriginal_title());
                contentValues.put(MovieContract.MovieEntry.COL_OVERVIEW, trailer.getOverview());
                contentValues.put(MovieContract.MovieEntry.COL_POSTER, trailer.getPoster_path());
                contentValues.put(MovieContract.MovieEntry.COL_VOTE, trailer.getVote_average());
                Log.d(TAG, "insertMovieTable: " + contentValues.toString());
                listener.onInsertSuccess(contentValues);
            }
        }, 500);
    }
}