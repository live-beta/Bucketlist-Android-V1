
        package com.example.sam.bucketlist.api;

        import android.app.Activity;
        import android.app.usage.NetworkStats;
        import android.content.Context;
        import android.media.session.MediaSession;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;

        import com.example.sam.bucketlist.R;
        import com.example.sam.bucketlist.models.BucketListFields;
        import com.example.sam.bucketlist.models.BucketListPost;
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

    ArrayList<BucketListFields> bucketListData = new ArrayList<>();


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://peaceful-citadel-97706.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    UserFields userFields = new UserFields();



    final UserClient userClient = retrofit.create(UserClient.class);

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

    public void registerUser(String username, String password, String email){

        UserFields userFields = new UserFields(username,password, email);

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

    public void addBucketList(String Token, String name) throws JSONException{

        BucketListFields bucketListFields = new BucketListFields(name);

        String tokenHeader = "Bearer " + Token;


        BucketListPost bucketListObj = new BucketListPost(bucketListFields.getBucketListName());


        Call<BucketListPost> call = userClient.addBucketList(tokenHeader,bucketListObj);

        call.enqueue(new Callback<BucketListPost>() {
            @Override
            public void onResponse(Call<BucketListPost> call, Response<BucketListPost> response) {

                Log.d("Bucketlist Added", response.message());
            }

            @Override
            public void onFailure(Call<BucketListPost> call, Throwable t) {
                Log.d("Fail","Failed to add ");
            }
        });

    }


    public void getBucketLists(String Token, final Context context) throws JSONException {


        String tokenHeader = "Bearer " + Token;
        Call<ArrayList<BucketListFields>> call = userClient.getBucketlist(tokenHeader);


        call.enqueue(new Callback<ArrayList<BucketListFields>>() {

            @Override
            public void onResponse(Call<ArrayList<BucketListFields>> call,
                                   Response<ArrayList<BucketListFields>> response) {


                try {

                    bucketListData = response.body();

                    Log.d("Data:", String.valueOf(bucketListData));

                    RecyclerView recyclerView = ((Activity)context)
                            .findViewById(R.id.bucketlistViewer);

                    BucketListAdapter adapter = new BucketListAdapter(context, bucketListData);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());



                }catch (Exception e){
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<BucketListFields>> call, Throwable t) {

            }
        });


    }

}
