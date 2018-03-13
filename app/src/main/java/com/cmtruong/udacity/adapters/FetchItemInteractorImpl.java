package com.cmtruong.udacity.adapters;

import android.os.Handler;
import android.util.Log;

import com.cmtruong.udacity.api.MovieServices;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Page;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by davidetruong on 13/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class FetchItemInteractorImpl implements FetchItemInteractor {
    private static final String TAG = FetchItemInteractorImpl.class.getSimpleName();


    @Override
    public void fetchItem(final onFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: done");
                listener.onFinished(fetchDataPopular());
            }
        }, 1000);
    }

    private List<Movie> fetchDataPopular() {
        Log.i(TAG, "fetchData: begin ...");
        final List<Movie> movies = new ArrayList<>();
        MovieServices movieServices = MovieServices.retrofit.create(MovieServices.class);
        Call<Page> myPage = movieServices.requestPopularMovies(1);
        myPage.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                int statusCode = response.code();
                if (response.isSuccessful() && statusCode == 200) {
                    Page page = response.body();
                    if (page != null)
                        movies.addAll(page.getMovies());
                    Log.i(TAG, "onResponse: " + movies.toString());
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return movies;
    }
}
