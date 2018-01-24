package com.example.sam.bucketlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Bucketlist Data Model
 */

public class BucketListFields {

    // POJO

    @SerializedName("creator")
    @Expose
    private String creator;

    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

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

    public BucketListFields(String name) {
        this.name = name;

    }
    public BucketListFields(){
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

    public void setBucketLists(ArrayList<BucketListFields> bucketLists){
        this.bucketLists = bucketLists;
    }
    public ArrayList<BucketListFields> getBucketLists(){
        return  this.bucketLists;
    }

}
