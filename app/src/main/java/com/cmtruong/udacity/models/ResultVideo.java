package com.cmtruong.udacity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author davidetruong
 * @version 1.0
 * @since April 9th, 2018
 */
public class ResultVideo {

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Video> videos;

    public ResultVideo() {
    }


    public ResultVideo(int id, List<Video> videos) {
        this.id = id;
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "ResultVideo{" +
                "id=" + id +
                ", videos=" + videos +
                '}';
    }
}
