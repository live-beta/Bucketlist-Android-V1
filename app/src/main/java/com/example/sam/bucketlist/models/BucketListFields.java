package com.example.sam.bucketlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Bucketlist data model and serializer for the retrofit data instance
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
    public ArrayList<ItemFields> items;
    public ArrayList<BucketListFields> bucketLists;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

    public BucketListFields(String name) {
        this.name = name;

    }

    public BucketListFields() {
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

    public ArrayList<ItemFields> getItems() {
        return items;
    }

    public ArrayList<BucketListFields> getBucketLists() {
        return this.bucketLists;
    }

    public void setBucketLists(ArrayList<BucketListFields> bucketLists) {
        this.bucketLists = bucketLists;
    }

}
