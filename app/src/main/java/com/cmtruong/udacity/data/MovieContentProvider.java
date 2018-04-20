package com.cmtruong.udacity.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cmtruong.udacity.configs.Config;

import static com.cmtruong.udacity.configs.Config.MOVIES;
import static com.cmtruong.udacity.configs.Config.MOVIE_WITH_ID;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 18th, 2018
 */
public class MovieContentProvider extends ContentProvider {

    private static final String TAG = MovieContentProvider.class.getSimpleName();
    private MovieDbHelper mDbHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    /**
     * Init a new matcher object without any matcher the use addUri function to add matches
     *
     * @return uriMatcher
     */
    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_MOVIE, MOVIES);
        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_MOVIE + "/#", Config.MOVIE_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDbHelper = new MovieDbHelper(context);
        Log.d(TAG, "onCreate: Done");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        Cursor returnCursor;

        switch (match) {
            case MOVIES:
                returnCursor = db.query(MovieContract.MovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri = null;

        switch (match) {
            case MOVIES:
                // Insert new value into the database
                long id = insertOrUpdateById(db, uri, MovieContract.MovieEntry.TABLE_NAME, values, MovieContract.MovieEntry.COL_ID);

                //long id = db.insertOrThrow(MovieContract.MovieEntry.TABLE_NAME, null, values);
                //Log.d(TAG, "insert: " + id);
                if (id > 0)
                    returnUri = ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI, id);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int moviesDeleted;
        switch (match) {
            case MOVIE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                moviesDeleted = db.delete(MovieContract.MovieEntry.TABLE_NAME, "movie_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (moviesDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return moviesDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int count = 0;// Count to tell number of rows updated
        switch (sUriMatcher.match(uri)) {
            case MOVIES:
                Log.d(TAG, "update: checked");
                count = db.update(MovieContract.MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private long insertOrUpdateById(SQLiteDatabase db, Uri uri, String table, ContentValues values, String column) throws SQLException {
        long id = 0;
        try {
            id = db.insertOrThrow(table, null, values);
        } catch (SQLiteConstraintException e) {
            int nrRows = update(uri, values, column + "=?",
                    new String[]{values.getAsString(column)});
            if (nrRows == 0)
                throw e;
        }
        return id;
    }
}
