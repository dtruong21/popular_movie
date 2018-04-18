package com.cmtruong.udacity.data;

import android.net.Uri;
import android.provider.BaseColumns;

import com.cmtruong.udacity.configs.Config;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 17th, 2018
 */
public class MovieContract {

    /**
     * MovieEntry is an inner class that defines the contents of the movie table
     */
    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Config.BASE_CONTENT_URI.buildUpon().appendPath(Config.PATH_MOVIE).build();

        public static final String TABLE_NAME = "movie";
        public static final String COL_ID = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_OVERVIEW = "overview";
        public static final String COL_DATE = "release_date";
        public static final String COL_VOTE = "vote_average";
        public static final String COL_POSTER = "poster_path";
        public static final String COL_ORI_TITLE = "original_title";
        public static final String COL_LANGUAGE = "original_language";
        public static final String COL_BACKDROP = "backdrop_path";
    }
}
