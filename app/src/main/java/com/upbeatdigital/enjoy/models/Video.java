package com.upbeatdigital.enjoy.models;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private String video;

    @SerializedName("thumb_nail")
    private String thumb_nail;

    @SerializedName("likes")
    private int likes;

    @SerializedName("views")
    private int views;

    @SerializedName("title")
    private String title;

    @SerializedName("liked")
    private boolean liked;

    @SerializedName("description")
    private String description;

    @SerializedName("artist")
    private Artist artist;

    @SerializedName("category")
    private Category category;

    public int getId() {
        return id;
    }

    public String getVideo() {
        return video;
    }

    public String getThumb_nail() {
        return thumb_nail;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public String getTitle() {
        return title;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getDescription() {
        return description;
    }

    public Artist getArtist() {
        return artist;
    }

    public Category getCategory() {
        return category;
    }
}
