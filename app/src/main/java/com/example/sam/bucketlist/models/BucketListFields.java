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

    @SerializedName("items")
    @Expose
    public ArrayList items;

    public BucketListFields(String name) {
        this.name = name;

    }
    public BucketListFields(ArrayList items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String idEntry) {
        this.id = idEntry;
    }

    public String getBucketListName() {
        return name;
    }

    public ArrayList getItems() {
        return items;
    }
}
