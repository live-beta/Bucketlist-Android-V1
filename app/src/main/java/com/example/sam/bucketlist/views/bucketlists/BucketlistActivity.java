package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;

import org.json.JSONException;


public class BucketlistActivity extends AppCompatActivity implements View.OnClickListener {

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

        try {
            APIManager apiManager = new APIManager(getApplicationContext());
            apiManager.getBucketLists(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}



