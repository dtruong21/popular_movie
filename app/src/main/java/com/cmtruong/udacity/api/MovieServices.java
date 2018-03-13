package com.cmtruong.udacity.api;

import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Page;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by davidetruong on 12/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public interface MovieServices {

    @GET(Config.POPULAR + Config.API_KEY + Config.API_LANGUAGE)
    Call<Page> requestPopularMovies(@Query("page") int page);

    @GET(Config.TOP_RATED + Config.API_KEY + Config.API_LANGUAGE)
    Call<List<Movie>> requestTopRatedMovies(@Query("page") int page);

    @GET("{movie_id}?api_key=" + Config.API_KEY + Config.API_LANGUAGE)
    Call<Movie> requestMovie(@Path("movie_id") int id);

    @GET(Config.POPULAR + Config.API_KEY + Config.API_LANGUAGE + Config.PAGE_NUMBER + "1")
    Call<JSONObject> requestMovies();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}

