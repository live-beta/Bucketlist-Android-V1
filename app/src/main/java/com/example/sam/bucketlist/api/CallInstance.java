package com.example.sam.bucketlist.api;

import com.example.sam.bucketlist.api.services.NetworkService;

/**
 * Created by sam on 1/25/18.
 */

public class CallInstance {

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
