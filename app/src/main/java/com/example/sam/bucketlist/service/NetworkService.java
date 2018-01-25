package com.example.sam.bucketlist.service;

import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.BucketListPost;
import com.example.sam.bucketlist.models.DeletePost;
import com.example.sam.bucketlist.models.ItemPost;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserFields;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Http Client Interface
 */

public interface NetworkService {


    @POST("auth/login")
    Call<UserFields> login(@Body LoginFields login);

    @POST("bucketlists")
    Call<BucketListPost> addBucketList(@Body BucketListPost name);

    @GET("bucketlists")
    Call<ArrayList<BucketListFields>> getBucketlist();

    @POST("auth/register")
    Call<UserFields> registerUser(@Body UserFields userFields);

    @POST("bucketlists/{id}/items")
    Call<ItemPost> addItems( @Path("id") String id, @Body ItemPost name);

    @DELETE("bucketlists/{id}")
    Call<DeletePost> deleteBucketList(@Path("id") String id);


}
