package com.example.sam.bucketlist.api;

import android.content.Context;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.BucketListPost;
import com.example.sam.bucketlist.models.CallInstanceModel;
import com.example.sam.bucketlist.models.DeletePost;
import com.example.sam.bucketlist.models.ItemFields;
import com.example.sam.bucketlist.models.ItemPost;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserDetailPost;
import com.example.sam.bucketlist.models.UserFields;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Class APIManager Application Operations
 */

public class APIManager {

    public static ArrayList<BucketListFields> bucketListValues;
    UserFields userFields = new UserFields();
    CallInstanceModel callInstance = new CallInstanceModel();

    private Context context;
    private String username, password, email;
    private String url;

    public APIManager(Context context) {

        this.url = context.getResources().getString(R.string.base_url);
        this.callInstance.setInstance(RetrofitInstance.retrofitInstance(this.url, context));
        this.context = context;

    }

    public APIManager(String userName, String password, Context context) {

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);

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

    /* API call for login*/

    public Call<UserFields> login() {

        LoginFields loginFields = new LoginFields(userFields.getUserName(), userFields.getPassword());

        return callInstance.getInstance().login(loginFields);
    }

    /* API call to Register a new User*/

    public Call<UserDetailPost> registerUser(String username, String password, String email) {

        UserFields userFields = new UserFields(username, password, email);
        UserDetailPost userDetailPost = new UserDetailPost(userFields.getUserName(),
                userFields.getPassword(), userFields.getEmail());

        return callInstance.getInstance().registerUser(userDetailPost);
    }

    /* API call to add a new bucketlist*
     */

    public Call<BucketListPost> addBucketList(String name, String description) {

        BucketListFields bucketListFields = new BucketListFields(name, description);
        BucketListPost bucketListPost = new BucketListPost(bucketListFields
                .getBucketListName(), bucketListFields.getBucketListDescription());

        return callInstance.getInstance().addBucketList(bucketListPost);

    }


    /* API call to add Items*/
    public Call<ItemPost> addItems(String name, String bucketListId) {

        ItemFields itemFields = new ItemFields(name);
        ItemPost itemPost = new ItemPost(itemFields.getName(), bucketListId);
        return callInstance.getInstance().addItems(bucketListId, itemPost);

    }

    /*API call to delete bucketlist*/

    public Call<DeletePost> deleteBucketList(String id) {

        return callInstance.getInstance().deleteBucketList(id);
    }

    /* Get bucketlist instance*/
    public Call<ArrayList<BucketListFields>> getBucketListsResponse() {
        return callInstance.getInstance().getBucketlist();
    }


}
