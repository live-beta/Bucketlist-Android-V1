package com.example.sam.bucketlist.models.retrofitmodels;

/**
 * Instance variables for for psting new items
 */

public class ItemPost {

    private String name;
    private String id;
    private String description;

    public ItemPost(String name, String id, String description) {
        this.name = name;
        this.description = description;
        this.id = id;

    }
}
