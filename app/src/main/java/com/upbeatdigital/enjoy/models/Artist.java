package com.upbeatdigital.enjoy.models;

import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private int price;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
