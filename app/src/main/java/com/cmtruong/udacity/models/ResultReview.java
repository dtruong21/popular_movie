package com.cmtruong.udacity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReview {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Review> reviews;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_result;


    public ResultReview() {
    }

    public ResultReview(int id, int page, List<Review> reviews, int total_pages, int total_result) {
        this.id = id;
        this.page = page;
        this.reviews = reviews;
        this.total_pages = total_pages;
        this.total_result = total_result;
    }

    @Override
    public String toString() {
        return "ResultReview{" +
                "id=" + id +
                ", page=" + page +
                ", reviews=" + reviews +
                ", total_pages=" + total_pages +
                ", total_result=" + total_result +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }
}
