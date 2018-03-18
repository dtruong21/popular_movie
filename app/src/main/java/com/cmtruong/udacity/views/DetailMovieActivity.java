package com.cmtruong.udacity.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author davidetruong
 * @version 1.0
 */
public class DetailMovieActivity extends Activity {

    private static final String TAG = DetailMovieActivity.class.getSimpleName();

    @BindView(R.id.iv_movie)
    ImageView imageView;
    @BindView(R.id.sv_movie)
    ScrollView scrollView;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_vote_average)
    TextView voteAverage;
    @BindView(R.id.tv_overview)
    TextView overview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(Config.MOVIE_INTENT_KEY);
        loadData(movie);

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void loadData(Movie movie) {
        title.setText(movie.getTitle());

        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            Date dateFormat = format.parse(movie.getRelease_date());
            date.setText(dateFormat.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        voteAverage.setText(String.valueOf(movie.getVote_average()));
        overview.setText(movie.getOverview());

        Picasso.get()
                .load(Config.IMAGE_URL + movie.getPoster_path())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }


}
