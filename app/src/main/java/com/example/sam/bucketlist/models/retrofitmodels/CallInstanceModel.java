package com.example.sam.bucketlist.models.retrofitmodels;

import com.example.sam.bucketlist.services.NetworkService;

/**
 * Created by sam on 1/25/18.
 */

public class CallInstanceModel {

    private NetworkService instance;
    private String url;

    public NetworkService getInstance() {
        return this.instance;
    }

    public void setInstance(NetworkService instance) {
        this.instance = instance;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
