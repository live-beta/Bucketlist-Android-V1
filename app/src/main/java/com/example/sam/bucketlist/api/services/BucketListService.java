package com.example.sam.bucketlist.api.services;

import com.example.sam.bucketlist.model.BucketListFields;

import java.util.ArrayList;

import io.reactivex.Observable;

import retrofit2.http.GET;

/**
 * Created by sam on 3/21/18.
 */

public interface BucketListService {

    @GET("bucketlists?limit=1000")
    Observable<ArrayList<BucketListFields>> fetchBucketLists();

}
