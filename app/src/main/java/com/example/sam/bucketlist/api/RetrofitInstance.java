package com.example.sam.bucketlist.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.sam.bucketlist.service.NetworkService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam on 1/29/18.
 */

public class RetrofitInstance {

    public static NetworkService retrofitInstance(String url, Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = sharedPreferences.getString("token", "");

        Log.d("Token in net ", token);

        /* Gson definition for faulty JSON*/

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        /* Inerceptor for token headers*/

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request modifiedRequest = chain.request().newBuilder().
                        addHeader("Authorization", "Bearer " + token).build();
                return chain.proceed(modifiedRequest);
            }
        };

        /*Adding interceptor to OkHTTPClient*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Log.d("Url passed", url);

        Retrofit.Builder retroBuild = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);


        Retrofit retrofit = retroBuild.build();

        return retrofit.create(NetworkService.class);
    }
}
