package com.example.sam.bucketlist.services;

import com.example.sam.bucketlist.models.datamodels.BucketListFields;
import com.example.sam.bucketlist.models.retrofitmodels.BucketListPost;
import com.example.sam.bucketlist.models.retrofitmodels.DeletePost;
import com.example.sam.bucketlist.models.retrofitmodels.ItemPost;
import com.example.sam.bucketlist.models.datamodels.LoginFields;
import com.example.sam.bucketlist.models.retrofitmodels.UserDetailPost;
import com.example.sam.bucketlist.models.datamodels.UserFields;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Http Client Interface
 */

public interface NetworkService {


    /* Defined API call contracts*/

    @POST("auth/login")
    Call<UserFields> login(@Body LoginFields login);

    @POST("bucketlists")
    Call<BucketListPost> addBucketList(@Body BucketListPost name);

    @GET("bucketlists?limit=1000")
    Call<ArrayList<BucketListFields>> getBucketlist();

    @POST("auth/register")
    Call<UserDetailPost> registerUser(@Body UserDetailPost userDetailPost);

    @POST("bucketlists/{id}/items")
    Call<ItemPost> addItems(@Path("id") String id, @Body ItemPost name);

    @DELETE("bucketlists/{id}")
    Call<DeletePost> deleteBucketList(@Path("id") String id);


}