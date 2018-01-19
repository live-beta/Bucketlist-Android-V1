package com.example.sam.bucketlist.models;

import com.google.gson.annotations.SerializedName;

/**
 * The class sets and gets Item fields
 */

public class ItemFields {


    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public ItemFields(String name) {
        this.name = name;
    }

    public void setItemID(String itemIDEntry) {
        this.id = itemIDEntry;

    }

    public String getItemId() {
        return id;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemNameEntry) {
        this.name = itemNameEntry;
    }


}
