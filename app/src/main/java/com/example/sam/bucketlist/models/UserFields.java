package com.example.sam.bucketlist.models;

/**
 * Class for user variables
 */

public class UserFields {

    private String username;
    private String password;
    private String token;
    private String email;

    public UserFields(String userName, String password, String email) {
        this.username = userName;
        this.password = password;
        this.email = email;
    }

    public UserFields() {

    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

}
