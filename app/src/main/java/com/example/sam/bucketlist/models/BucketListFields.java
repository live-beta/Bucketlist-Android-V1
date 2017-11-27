package com.example.sam.bucketlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 9/19/17.
 */

public class BucketListFields {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    public void setId(String idEntry){
        this.id =idEntry;
    }
    public String getId(){
        return id;
    }

    public void setBucketListName(String bucketListNameEntry){
        this.name = bucketListNameEntry;
    }
    public String getBucketListName(){
        return name;
    }


}
