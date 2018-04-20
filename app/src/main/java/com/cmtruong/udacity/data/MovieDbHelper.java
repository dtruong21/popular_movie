package com.cmtruong.udacity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmtruong.udacity.configs.Config;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 17th, 2018
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_MOVIE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME
            + " ("
            + MovieContract.MovieEntry.COL_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + MovieContract.MovieEntry.COL_TITLE + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_OVERVIEW + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_DATE + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_VOTE + " REAL NOT NULL,"
            + MovieContract.MovieEntry.COL_ORI_TITLE + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_LANGUAGE + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_BACKDROP + " TEXT NOT NULL,"
            + MovieContract.MovieEntry.COL_POSTER + " TEXT NOT NULL,"
            + "UNIQUE(" + MovieContract.MovieEntry.COL_ID + ") ON CONFLICT REPLACE"
            + ");";

    private static final String SQL_DROP_MOVIE = "DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;

    public MovieDbHelper(Context context) {
        super(context, Config.DATABASE_MOVIE, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_MOVIE);
        onCreate(db);
    }
}
