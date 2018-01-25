package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.BucketListFields;

import org.json.JSONException;


public class BucketlistActivity extends AppCompatActivity implements View.OnClickListener {


    private BucketListFields bucketListFields = new BucketListFields();
    private Context context = this;
    private FloatingActionButton newBucketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        newBucketList = findViewById(R.id.floatingButton);
        newBucketList.setOnClickListener(this);
        loadBucketLists();
    }

    @Override
    public void onClick(View view) {

        if (view == newBucketList) {
            Intent intent = new Intent(context, AddBucketList.class);
            startActivity(intent);
        }
    }

    public void loadBucketLists() {

        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString("token", "");

        Log.d("Token Being passed ", token);

        if (token == null) {
            Toast.makeText(this, "You are not logged in try again",
                    Toast.LENGTH_LONG).show();
        } else {

            try {
                APIManager apiManager = new APIManager(getApplicationContext());
                apiManager.getBucketLists(token, context);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

}


