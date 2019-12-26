package com.upbeatdigital.enjoy.api.responsemodels;

import com.google.gson.annotations.SerializedName;
import com.upbeatdigital.enjoy.models.Ad;
import com.upbeatdigital.enjoy.models.Slider;
import com.upbeatdigital.enjoy.models.Video;

import java.util.List;

public class ResponseGetHome {

    @SerializedName("status_code")
    private int status_code;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Data data;

    public int getStatus_code() {
        return status_code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public class ResponseVideo {

        @SerializedName("videos")
        private List<Video> videos;

        @SerializedName("next_page")
        private String next_page;

        @SerializedName("ad")
        private Ad ad;

        public List<Video> getVideos() {
            return videos;
        }

        public String getNext_page() {
            return next_page;
        }

        public Ad getAd() {
            return ad;
        }
    }

    public class Data{
        @SerializedName("slider")
        private List<Slider> sliderList;

        @SerializedName("videos")
        private ResponseVideo responseVideo;

        public List<Slider> getSliderList() {
            return sliderList;
        }

        public ResponseVideo getResponseVideo() {
            return responseVideo;
        }
    }
}
