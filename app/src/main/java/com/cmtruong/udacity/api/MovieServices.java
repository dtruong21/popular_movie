package com.cmtruong.udacity.api;

import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Page;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    @GET(Config.POPULAR)
    Call<Page> requestPopularMovies(@Query("api_key") String apiKey);

    @GET(Config.TOP_RATED)
    Call<List<Movie>> requestTopRatedMovies(@Query("api_key") String apiKey);

    @GET("{sort}")
    Call<Page> requestMovies(@Path("sort") String sort, @Query("api_key") String apiKey);

    @GET("{movie_id}?api_key=")
    Call<Movie> requestMovie(@Path("movie_id") int id);

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "popular-movie").build();
            return chain.proceed(newRequest);
        }
    };

    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

