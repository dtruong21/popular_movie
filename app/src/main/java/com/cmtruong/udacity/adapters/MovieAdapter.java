package com.cmtruong.udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by davidetruong on 13/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private static final String TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(@NonNull Context context, @NonNull List<Movie> objects) {
        super(context, 0, objects);
        Log.i(TAG, "MovieAdapter: begin ...");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.poster_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (null != movie) {
            Picasso.get()
                    .load(Config.IMAGE_URL + movie.getPoster_path())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageView);
            String rated = String.valueOf(movie.getVote_average()) + "/10";

            //viewHolder.textView.setText(rated);
        }


        Log.i(TAG, "getView: checked");
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_grid_item)
        ImageView imageView;

//        @BindView(R.id.tv_rated_average)
//        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
