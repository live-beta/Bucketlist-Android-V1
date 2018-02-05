package com.example.sam.bucketlist.api;

import com.example.sam.bucketlist.models.BucketListFields;

import java.util.ArrayList;

/**
 * Created by sam on 1/29/18.
 */

public class ApiResponse {

    private ArrayList<BucketListFields> bucketListFieldsArrayList;
    private Throwable throwable;

    ApiResponse(ArrayList<BucketListFields> bucketListFieldsArrayList, Throwable throwable){
        this.throwable = throwable;
        this.bucketListFieldsArrayList = bucketListFieldsArrayList;
    }

    ApiResponse(ArrayList<BucketListFields> bucketListFieldsArrayList){
        this.bucketListFieldsArrayList = bucketListFieldsArrayList;
    }

    ApiResponse(Throwable throwable){
        this.throwable = throwable;
    }

    public ArrayList<BucketListFields> getBucketListFieldsArrayList() {
        return bucketListFieldsArrayList;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
