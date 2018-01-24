package com.example.sam.bucketlist.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The class sets and gets Item fields
 */

public class ItemFields {


    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private Object dateModified;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    private ArrayList<ItemFields> items;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getDateModified() {
        return dateModified;
    }

    public void setDateModified(Object dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Setting up the items data, passing the  thetougn tht set data

    public void setItems(ArrayList<ItemFields> items){
        this.items = items;
    }

    public ArrayList<ItemFields> getItems (){
        return this.items;
    }


}
