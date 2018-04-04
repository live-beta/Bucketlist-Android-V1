package com.example.sam.bucketlist.api.services;

import com.example.sam.bucketlist.model.BucketListFields;
import com.example.sam.bucketlist.model.LoginFields;
import com.example.sam.bucketlist.model.UserRegistrationFields;
import com.example.sam.bucketlist.model.UserLoginFields;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Http Client Interface
 */

public interface NetworkService {


    /* Defined API call contracts*/

    @POST("auth/login")
    Call<UserLoginFields> login(@Body LoginFields login);

    @GET("bucketlists?limit=1000")
    Call<ArrayList<BucketListFields>> getBucketlist();

    @POST("auth/register")
    Call<UserRegistrationFields> registerUser(@Body UserRegistrationFields userRegistrationFields);


}
