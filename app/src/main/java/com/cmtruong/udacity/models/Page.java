package com.cmtruong.udacity.models;

import java.util.List;

/**
 * Created by davidetruong on 12/03/2018.
 * @author davidetruong
 * @version 1.0
 */

public class Page {
    public int page;
    public List<Movie> movies;
    public int total_results, total_page;

    public Page() {
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
}
