package com.cmtruong.udacity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davidetruong on 12/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class Page {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> movies;
    @SerializedName("total_pages")
    private int total_page;
    @SerializedName("total_results")
    private int total_results;

    public Page() {
    }

    public Page(int page, List<Movie> movies, int total_page, int total_results) {
        this.page = page;
        this.movies = movies;
        this.total_page = total_page;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", movies=" + movies +
                ", total_page=" + total_page +
                ", total_results=" + total_results +
                '}';
    }
}
