package com.example.sam.bucketlist.models;

/**
 * Created by sam on 1/31/18.
 */

public class UserDetailPost {


    private String username;
    private String password;
    private String email;

    public UserDetailPost(String username, String password, String email) {
        this.username = username;

        this.password = password;

        this.email = email;
    }


}
