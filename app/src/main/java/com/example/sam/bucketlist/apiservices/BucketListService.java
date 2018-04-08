package com.example.sam.bucketlist.apiservices;

import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserLoginFields;

import java.util.ArrayList;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sam on 3/21/18.
 */

public interface BucketListService {

    @GET("bucketlists?limit=1000")
    Observable<ArrayList<BucketListFields>> fetchBucketLists();


    @POST("auth/login")
    Call<UserLoginFields> login(@Body LoginFields login);

}
