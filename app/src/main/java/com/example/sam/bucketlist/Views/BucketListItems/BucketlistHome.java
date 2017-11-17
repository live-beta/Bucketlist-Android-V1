package com.example.sam.bucketlist.Views.BucketListItems;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sam.bucketlist.BucketListMethods.BucketList;
import com.example.sam.bucketlist.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


public class BucketlistHome extends Activity{

    private BucketList bucketList = new BucketList();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        ArrayList<HashMap> bucketlistData = new ArrayList<>();

        try {

            bucketlistData = bucketList.getBucketLists();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadBucketLists(bucketlistData);

    }
    private void loadBucketLists(ArrayList<HashMap> bucketlistData){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bucketlistViewer);

        BucketListAdapter adapter = new BucketListAdapter(this, bucketlistData);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }

}


