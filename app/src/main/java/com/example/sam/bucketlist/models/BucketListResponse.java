package com.example.sam.bucketlist.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sam on 3/28/18.
 */

public class BucketListResponse {


    @SerializedName("")

    private ArrayList<BucketListFields> bucketlists;

    public ArrayList<BucketListFields> getBucketlists() {


        return bucketlists;
    }

    public void setBucketlists(ArrayList<BucketListFields> bucketlists) {
        this.bucketlists = bucketlists;
    }


}
