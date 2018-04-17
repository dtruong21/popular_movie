package com.cmtruong.udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.configs.Config;
import com.cmtruong.udacity.models.Movie;
import com.cmtruong.udacity.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private static final String TAG = TrailerAdapter.class.getSimpleName();

    private List<Video> trailers;
    private final OnItemClickListener mListener;

    public TrailerAdapter(List<Video> trailers, OnItemClickListener listener) {
        this.trailers = trailers;
        mListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(Video video);
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForVideoItem = R.layout.item_trailer;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForVideoItem, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Video video = trailers.get(position);
        Log.d(TAG, "onBindViewHolder: " + video.toString());
        holder.bind(video, mListener);
    }

    @Override
    public int getItemCount() {
        if (null == trailers)
            return 0;
        return trailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_trailer)
        ImageView iv_trailer;
        @BindView(R.id.tv_name_video)
        TextView tv_name_video;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Video video, final OnItemClickListener listener) {
            tv_name_video.setText(video.getName());

            Picasso.get()
                    .load(Config.YOUTUBE_URL + video.getKey() + "/0.jpg")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background).into(iv_trailer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(video);
                }
            });
        }


    }
}
