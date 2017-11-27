package com.example.sam.bucketlist.models;

/**
 * Class for user variables
 */

public class UserFields {

    private String userName;
    private String password;
    private String token;

   public void setUserName(String name){
       this.userName = name;
   }
   public String getUserName(){
       return this.userName;
   }
   public void setToken(String token){
       this.token =token;
   }
    public String getToken(){
       return  this.token;
    }
   public void setPassword(String userPassword){
       this.password = userPassword;
   }
   public String getPassword(){
       return this.password;
   }
}
