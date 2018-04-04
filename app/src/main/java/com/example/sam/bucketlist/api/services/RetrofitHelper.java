package com.example.sam.bucketlist.api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sam on 3/28/18.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMOUT = 10;
    private Retrofit retrofit;
    private BucketListService bucketListService;
    OkHttpClient.Builder builder;
    private Scheduler scheduler;
    private static Interceptor interceptor;
    private static String Instancetoken;

    private static class Singleton{
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();

    }

    public static RetrofitHelper getInstance(String token){

        Instancetoken = token;
        return Singleton.INSTANCE;
    }

    private RetrofitHelper() {

        interceptor = chain -> {
            Request modifiedRequest = chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " +Instancetoken)
                    .build();
            return chain.proceed(modifiedRequest);
        };

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.connectTimeout(DEFAULT_TIMOUT, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        Retrofit.Builder retrofit = new Retrofit.Builder().baseUrl ("http://10.0.2.2:5000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);

        Retrofit instance = retrofit.build();
        bucketListService = instance.create(BucketListService.class);
    }



}
