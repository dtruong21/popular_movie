package com.cmtruong.udacity.data;

import android.provider.BaseColumns;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 17th, 2018
 */
public class MovieContract {

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String COL_ID = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_OVERVIEW = "overview";
        public static final String COL_DATE = "release_date";
        public static final String COL_VOTE = "vote_average";
        public static final String COL_POSTER = "poster_path";
    }
}
