package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;

import org.json.JSONException;


public class BucketlistActivity extends AppCompatActivity {

    private APIManager bucketList = new APIManager();

    private Context context = this;

    private FloatingActionButton newBucketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        newBucketList = findViewById(R.id.floatingButton);

        newBucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(context, AddBucketList.class);
               startActivity(intent);

            }
        });

        loadBucketLists();

    }

    public void loadBucketLists() {


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String token = preferences.getString("token", "");


        /*Check if the token is null*/

        if (token ==null){
            Toast.makeText(this,"You are not logged in try again", Toast.LENGTH_LONG).show();
        }else {
            try {
                bucketList.getBucketLists(token, context);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }





    }

}


