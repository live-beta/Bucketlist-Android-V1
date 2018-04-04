package com.example.sam.bucketlist.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Bucketlist data model and serializer for the retrofit instance
 */

public class BucketListFields implements Serializable{

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("items")
    @Expose
    public ArrayList<ItemFields> items;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;


}
