package com.example.sam.bucketlist.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.example.sam.bucketlist.apiservices.BucketListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam on 3/21/18.
 */

public class ApiFactory {

    private static Context appContext;
    private static Interceptor interceptor;

    public ApiFactory(Context context){

       this.appContext = context;


    }

    public static BucketListService create(Context context){

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        final String token = sharedPreferences.getString("token", "");


        interceptor = chain -> {
            Request modifiedRequest = chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " +token)
                    .build();
            return chain.proceed(modifiedRequest);
        };



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit.Builder retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);

        Retrofit retrofitInstance = retrofit.build();
        return retrofitInstance.create(BucketListService.class);

    }
}
