package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


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
                Toast.makeText(getApplicationContext(),"Built on a button",Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<HashMap> bucketlistData = new ArrayList<>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);


        String token = preferences.getString("token", "");

        try {
            bucketlistData = bucketList.getBucketLists(token);

            Log.d("Data point here",String.valueOf(bucketlistData));
            loadBucketLists(bucketlistData);

        } catch (JSONException e) {
            e.printStackTrace();
        }






    }

    private void loadBucketLists(ArrayList<HashMap> bucketlistData) {

        RecyclerView recyclerView = findViewById(R.id.bucketlistViewer);

        BucketListAdapter adapter = new BucketListAdapter(this, bucketlistData);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

}


