package com.cmtruong.udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmtruong.udacity.R;
import com.cmtruong.udacity.models.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private static final String TAG = ReviewAdapter.class.getSimpleName();
    private List<Review> reviews;

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForReviewItem = R.layout.item_review;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForReviewItem, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Review review = reviews.get(position);
        Log.d(TAG, "onBindViewHolder: " + review.toString());
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        if (null == reviews)
            return 0;
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_review_author)
        TextView tv_review_author;
        @BindView(R.id.tv_review_content)
        TextView tv_review_content;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Review review) {
            tv_review_author.setText(review.getAuthor());
            tv_review_content.setText(review.getContent());
        }
    }
}
