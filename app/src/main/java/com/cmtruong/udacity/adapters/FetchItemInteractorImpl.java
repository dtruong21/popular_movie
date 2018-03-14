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

    private List<Movie> movies = new ArrayList<>();


    @Override
    public void fetchItem(final onFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //listener.onFinished(fetchDataPopular());
                Log.i(TAG, "fetchData: begin ...");
                MovieServices movieServices = MovieServices.retrofit.create(MovieServices.class);
                Call<Page> myPage = movieServices.requestPopularMovies(1);
                myPage.enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {
                        int statusCode = response.code();
                        Log.i(TAG, "onResponse: " + response.code());
                        if (response.isSuccessful() && statusCode == 200) {
                            movies = response.body().getMovies();
                            listener.onFinished(movies);
                            Log.i(TAG, "onResponse: " + movies.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Page> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
            }
        }, 1000);
    }


}
