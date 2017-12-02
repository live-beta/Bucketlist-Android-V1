package com.example.sam.bucketlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Bucketlist Data Model
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

    public String getBucketListName(){
        return name;
    }



}
