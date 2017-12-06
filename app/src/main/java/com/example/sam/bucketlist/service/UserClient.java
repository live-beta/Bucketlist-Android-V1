package com.example.sam.bucketlist.service;

import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.BucketListPost;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserFields;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Http Client Interface
 */

public interface UserClient {

    @POST("auth/login")
    Call <UserFields> login(@Body LoginFields login);

    @GET("bucketlists")
    Call<ArrayList<BucketListFields>> getBucketlist(@Header("Authorization") String token);

    @POST("bucketlists")
    Call<BucketListPost> addBucketList(@Header("Authorization") String token, @Body BucketListPost name);


}
