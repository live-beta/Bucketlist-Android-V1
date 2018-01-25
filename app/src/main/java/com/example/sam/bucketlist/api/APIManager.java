package com.example.sam.bucketlist.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.BucketListPost;
import com.example.sam.bucketlist.models.CallInstanceModel;
import com.example.sam.bucketlist.models.DeletePost;
import com.example.sam.bucketlist.models.ItemFields;
import com.example.sam.bucketlist.models.ItemPost;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserFields;
import com.example.sam.bucketlist.service.NetworkService;
import com.example.sam.bucketlist.views.bucketlists.BucketListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    UserFields userFields = new UserFields();
    CallInstanceModel callInstance = new CallInstanceModel();

    //.baseUrl("https://peaceful-citadel-97706.herokuapp.com/api/v1/")
    //baseUrl("http://10.0.2.2:5000/api/v1/")
    private String token;
    private NetworkService apiCall;
    private Context context;

    private NetworkService apiCalls;


    public APIManager(Context context) {

        this.callInstance.setInstance(retrofitInstance(context.getResources().
                getString(R.string.base_url), context));
        this.context = context;

    }

    public APIManager(String userName, String password, Context context) {

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);

        this.context = context;

        this.callInstance.setInstance(retrofitInstance(context.getResources()
                .getString(R.string.base_url), context));

    }

    public NetworkService retrofitInstance(String url, Context context) {

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

        Retrofit.Builder retroBuild = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);


        Retrofit retrofit = retroBuild.build();
        final NetworkService networkService = retrofit.create(NetworkService.class);

        return networkService;
    }


    public boolean login(Callback<UserFields> callback) {

        LoginFields loginObj = new LoginFields(userFields.getUserName(),
                userFields.getPassword());


        Call<UserFields> call = this.callInstance.getInstance().login(loginObj);
        call.enqueue(callback);

        return false;
    }


    public void registerUser(String username, String password, String email) {

        UserFields userFields = new UserFields(username, password, email);

        userFields.setUserName(username);
        userFields.setEmail(email);
        userFields.setPassword(password);

        Call<UserFields> call = apiCall.registerUser(userFields);

        call.enqueue(new Callback<UserFields>() {
            @Override
            public void onResponse(Call<UserFields> call, Response<UserFields> response) {
                Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserFields> call, Throwable t) {

            }
        });
    }

    public void addBucketList(String name) throws JSONException {

        BucketListFields bucketListFields = new BucketListFields(name);
        BucketListPost bucketListObj = new BucketListPost(bucketListFields
                .getBucketListName());
        Call<BucketListPost> call = this.callInstance.getInstance()
                .addBucketList(bucketListObj);

        call.enqueue(new Callback<BucketListPost>() {
            @Override
            public void onResponse(Call<BucketListPost> call,
                                   Response<BucketListPost> response) {

                Toast.makeText(context, "Bucketlist Added ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BucketListPost> call, Throwable t) {
                Toast.makeText(context, "Unable to Add Bucketlist",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addItems(String name, String bucketListId) {

        ItemFields itemFields = new ItemFields(name);
        ItemPost itemPost = new ItemPost(itemFields.getName(), bucketListId);
        Call<ItemPost> call = apiCall.addItems(bucketListId, itemPost);

        call.enqueue(new Callback<ItemPost>() {
            @Override
            public void onResponse(Call<ItemPost> call,
                                   Response<ItemPost> response) {
                Toast.makeText(context, "Item Added ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ItemPost> call, Throwable t) {

                Toast.makeText(context, "Not Added ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteBucketList(String id, final Context context) {

        Call<DeletePost> call = apiCall.deleteBucketList(id);
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

    public void getBucketLists(final Context context) throws JSONException {

        Call<ArrayList<BucketListFields>> call = this.callInstance
                .getInstance().getBucketlist();
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
