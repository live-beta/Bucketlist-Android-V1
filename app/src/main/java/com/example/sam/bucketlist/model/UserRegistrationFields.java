package com.example.sam.bucketlist.model;

/**
 * Instance variables for registration of another user
 */

public class UserRegistrationFields {


    private String username;
    private String password;
    private String email;

    public UserRegistrationFields(String username, String password, String email) {
        this.username = username;

        this.password = password;

        this.email = email;
    }

}
