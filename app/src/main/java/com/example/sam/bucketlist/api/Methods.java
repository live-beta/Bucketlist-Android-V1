package com.example.sam.bucketlist.api;

import android.util.Log;

import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.ItemFields;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserFields;
import com.example.sam.bucketlist.service.UserClient;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class Methods Application Operations
 */

public class Methods {

    ArrayList<BucketListFields> bucketListFieldsArrayList;
    ArrayList<HashMap> bucketListData = new ArrayList<>();

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    UserFields userFields = new UserFields();

    final UserClient userClient = retrofit.create(UserClient.class);

    public Methods() {

    }

    public Methods(String userName, String password) {

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);
    }

    public boolean login(Callback<UserFields> callback) {

        LoginFields loginObj = new LoginFields(userFields.getUserName(), userFields.getPassword());
        Call<UserFields> call = userClient.login(loginObj);
        call.enqueue(callback);

        return false;
    }

    public ArrayList<HashMap> getBucketLists(String Token) throws JSONException {

        String tokenHeader = "Bearer " + Token;
        Call<ArrayList<BucketListFields>> call = userClient.getBucketlist(tokenHeader);

        call.enqueue(new Callback<ArrayList<BucketListFields>>() {

            @Override
            public void onResponse(Call<ArrayList<BucketListFields>> call, Response<ArrayList<BucketListFields>> response) {

                /**
                 * TODO
                 * Check on iteration.
                 */

                for (int index = 0; index < response.body().size() ; index++) {

                    HashMap Data = new HashMap();

                    Data.put("id",response.body().get(index).getId());
                    Data.put("name", response.body().get(index).getBucketListName());
                    bucketListData.add(Data);

                    Log.d("Bucketlist Data",bucketListData.toString());

                }

            }

            @Override
            public void onFailure(Call<ArrayList<BucketListFields>> call, Throwable t) {

            }
        });

        return bucketListData;

    }

    public ArrayList<BucketListFields> getBucketListsData(String Token) throws JSONException {

        String tokenHeader = "Bearer " + Token;

        Call<ArrayList<BucketListFields>> call = userClient.getBucketlist(tokenHeader);

        call.enqueue(new Callback<ArrayList<BucketListFields>>() {

            @Override
            public void onResponse(Call<ArrayList<BucketListFields>> call, Response<ArrayList<BucketListFields>> response) {

                bucketListFieldsArrayList =  response.body();

            }

            @Override
            public void onFailure(Call<ArrayList<BucketListFields>> call, Throwable t) {

            }
        });


        return bucketListFieldsArrayList;



    }
}

