package com.example.sam.bucketlist.models;

/**
 * Instance variables for registration of another user
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
