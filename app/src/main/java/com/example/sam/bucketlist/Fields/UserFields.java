package com.example.sam.bucketlist.Fields;

/**
 * Class for user variables
 */

public class UserFields {

    private String userName;
    private String password;

   public void setUserName(String name){
       this.userName = name;
   }
   public String getUserName(){
       return this.userName;
   }
   public void setPassword(String userPassword){
       this.password = userPassword;
   }
   public String getPassword(){
       return this.password;
   }
}
