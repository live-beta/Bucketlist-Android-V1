package com.example.sam.bucketlist.api;

import android.content.Context;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.model.BucketListFields;
import com.example.sam.bucketlist.model.LoginFields;
import com.example.sam.bucketlist.model.UserLoginFields;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Class APIManager Application Operations
 */

public class APIManager {

    public static ArrayList<BucketListFields> bucketListValues;
    UserLoginFields userLoginFields = new UserLoginFields();
    CallInstance callInstance = new CallInstance();

    private Context context;
    private String username, password, email;
    private String url;

    public APIManager(Context context) {

        this.url = context.getResources().getString(R.string.base_url);
        this.callInstance.setInstance(RetrofitInstance.retrofitInstance(this.url, context));
        this.context = context;

    }

    public APIManager(String userName, String password, Context context) {

        this.userLoginFields.setUserName(userName);
        this.userLoginFields.setPassword(password);

        this.url = context.getResources().getString(R.string.base_url);

        this.context = context;

        this.callInstance.setInstance(RetrofitInstance.retrofitInstance(url, context));

    }

    public APIManager(final Context context, String name, String userPassword, String userEmail) {

        this.context = context;
        this.username = name;
        this.password = userPassword;
        this.email = userEmail;

    }

    public Call<UserLoginFields> login() {

        LoginFields loginFields = new LoginFields(userLoginFields.getUserName(), userLoginFields.getPassword());

        return callInstance.getInstance().login(loginFields);
    }


}
