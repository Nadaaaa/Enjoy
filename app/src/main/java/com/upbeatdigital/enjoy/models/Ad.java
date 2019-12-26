package com.upbeatdigital.enjoy.models;

import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }
}
