package com.example.sam.bucketlist.views.bucketLists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.BucketListPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Activity for primaily adding a new bucketlist
 */

public class AddBucketList extends Activity {

    Button addBucketList;
    EditText newBucketList, bucketListDescription;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bucketlist);

        addBucketList = findViewById(R.id.addBucketlist);
        newBucketList = findViewById(R.id.newBucketList);
        bucketListDescription = findViewById(R.id.bucketListDescription);


        addBucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBucketlist = newBucketList.getText().toString();
                String description = bucketListDescription.getText().toString();


                APIManager apiManager = new APIManager(context);
                Call<BucketListPost> call = apiManager.addBucketList(newBucketlist, description);

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

                Intent intent = new Intent(context, BucketlistActivity.class);
                startActivity(intent);
            }
        });


    }
}
