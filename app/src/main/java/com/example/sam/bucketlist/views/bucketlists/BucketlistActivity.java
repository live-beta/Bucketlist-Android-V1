package com.example.sam.bucketlist.views.bucketlists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.BucketListFields;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BucketlistActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BucketlistActivity.class.getName();
    BucketListAdapter adapter;
    private Context context = this;
    private FloatingActionButton newBucketList;
    private APIManager apiManager;
    private ArrayList<BucketListFields> bucketArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        newBucketList = findViewById(R.id.floatingButton);
        newBucketList.setOnClickListener(this);
        apiManager = new APIManager(getApplicationContext());
        initialiseRecyclerView();
        loadBucketLists();

    }

    @Override
    public void onClick(View view) {

        if (view == newBucketList) {
            Intent intent = new Intent(context, AddBucketList.class);
            startActivity(intent);
        }
    }

    private void initialiseRecyclerView() {
        RecyclerView recyclerView = ((Activity) context)
                .findViewById(R.id.bucketlistViewer);

        adapter = new BucketListAdapter(context, bucketArrayList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void loadBucketLists() {

        Call<ArrayList<BucketListFields>> call = apiManager.getBucketListsResponse();
        call.enqueue(new Callback<ArrayList<BucketListFields>>() {
            @Override
            public void onResponse(Call<ArrayList<BucketListFields>> call,
                                   Response<ArrayList<BucketListFields>> response) {
                bucketArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<BucketListFields>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to load bucketlists",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}



