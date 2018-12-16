package com.oc.liza.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsImage {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("media-metadata")
    @Expose
    private ArrayList<MediaImage> metadata;

    public ArrayList<MediaImage> getMetadata() {
        return metadata;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
