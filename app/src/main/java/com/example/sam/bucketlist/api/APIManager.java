package com.example.sam.bucketlist.api;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.BucketListPost;
import com.example.sam.bucketlist.models.DeletePost;
import com.example.sam.bucketlist.models.ItemFields;
import com.example.sam.bucketlist.models.ItemPost;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserFields;
import com.example.sam.bucketlist.service.UserClient;
import com.example.sam.bucketlist.views.bucketlists.BucketListAdapter;

import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class APIManager Application Operations
 */

public class APIManager {

    ArrayList<BucketListFields> bucketListValues = new ArrayList<>();
    ArrayList<ItemFields> itemFields = new ArrayList<>();


    //.baseUrl("https://peaceful-citadel-97706.herokuapp.com/api/v1/")
    //baseUrl("http://10.0.2.2:5000/api/v1/")

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    final UserClient userClient = retrofit.create(UserClient.class);
    UserFields userFields = new UserFields();

    public APIManager() {

    }

    public APIManager(String userName, String password) {

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);
    }

    public boolean login(Callback<UserFields> callback) {

        LoginFields loginObj = new LoginFields(userFields.getUserName(), userFields.getPassword());
        Call<UserFields> call = userClient.login(loginObj);
        call.enqueue(callback);

        return false;
    }


    public void registerUser(String username, String password, String email) {

        UserFields userFields = new UserFields(username, password, email);

        userFields.setUserName(username);
        userFields.setEmail(email);
        userFields.setPassword(password);

        Call<UserFields> call = userClient.registerUser(userFields);

        call.enqueue(new Callback<UserFields>() {
            @Override
            public void onResponse(Call<UserFields> call, Response<UserFields> response) {
                Log.d("Registration", response.message());
            }

            @Override
            public void onFailure(Call<UserFields> call, Throwable t) {

            }
        });
    }


    public void addBucketList(String Token, String name) throws JSONException {

        BucketListFields bucketListFields = new BucketListFields(name);
        String tokenHeader = "Bearer " + Token;
        BucketListPost bucketListObj = new BucketListPost(bucketListFields.getBucketListName());

        Call<BucketListPost> call = userClient.addBucketList(tokenHeader, bucketListObj);

        call.enqueue(new Callback<BucketListPost>() {
            @Override
            public void onResponse(Call<BucketListPost> call, Response<BucketListPost> response) {

                Log.d("Bucketlist Added", response.message());
            }

            @Override
            public void onFailure(Call<BucketListPost> call, Throwable t) {
                Log.d("Fail", "Failed to add ");
            }
        });

    }

    public void addItems(String Token, String name, String bucketListId) {

        ItemFields itemFields = new ItemFields(name);
        String tokenHeader = "Bearer " + Token;
        ItemPost itemPost = new ItemPost(itemFields.getName(), bucketListId);
        Call<ItemPost> call = userClient.addItems(tokenHeader, bucketListId, itemPost);

        call.enqueue(new Callback<ItemPost>() {
            @Override
            public void onResponse(Call<ItemPost> call, Response<ItemPost> response) {
                Log.d("Success", response.message());
            }

            @Override
            public void onFailure(Call<ItemPost> call, Throwable t) {

                Log.d("Fail", "Unable to add Item");

            }
        });
    }

    public void deleteBucketList(String Token, String id, final Context context) {

        String tokenHeader = "Bearer " + Token;
        Call<DeletePost> call = userClient.deleteBucketList(tokenHeader, id);
        call.enqueue(new Callback<DeletePost>() {
            @Override
            public void onResponse(Call<DeletePost> call, Response<DeletePost> response) {
                Toast.makeText(context, "Deleted " + response.message(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DeletePost> call, Throwable t) {

                Toast.makeText(context, "Could not Delete " + t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getBucketLists(String Token, final Context context) throws JSONException {

        String tokenHeader = "Bearer " + Token;

        Log.d("Token at getter ", tokenHeader);
        Call<ArrayList<BucketListFields>> call = userClient.getBucketlist(tokenHeader);
        call.enqueue(new Callback<ArrayList<BucketListFields>>() {

            @Override
            public void onResponse(Call<ArrayList<BucketListFields>> call,
                                   Response<ArrayList<BucketListFields>> response) {
                try {

                    bucketListValues = response.body();

                    BucketListFields bucketListFields = new BucketListFields();

                    bucketListFields.setBucketLists(bucketListValues);


                    RecyclerView recyclerView = ((Activity) context)
                            .findViewById(R.id.bucketlistViewer);

                    BucketListAdapter adapter = new BucketListAdapter(context, bucketListValues);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<BucketListFields>> call, Throwable t) {
                Log.d("Error", String.valueOf(t.getMessage()));
            }
        });

    }


}
