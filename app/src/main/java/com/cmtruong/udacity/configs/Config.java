package com.cmtruong.udacity.configs;

import com.cmtruong.udacity.BuildConfig;

/**
 * Created by davidetruong on 12/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class Config {

    public static final String API_URL = "https://api.themoviedb.org/3/movie/";
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";

    // Value must to change when you want to test my app
    public static final String API_KEY = BuildConfig.API_KEY;

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185";

    // Movie's intent key to transfer
    public static final String MOVIE_INTENT_KEY = "movie";
}
