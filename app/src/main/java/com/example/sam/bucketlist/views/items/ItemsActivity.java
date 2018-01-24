package com.example.sam.bucketlist.views.items;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.ItemFields;

import java.util.ArrayList;

/**
 * Created by sam on 11/7/17.
 */

public class ItemsActivity extends Activity {

    ItemFields itemFields;
    private FloatingActionButton newItems;
    private String id;
    private Context context = this;
    private ArrayList<ItemFields> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_item_details);


        final Bundle extras = getIntent().getExtras();

        try {

            ArrayList items = (ArrayList) extras.get("items");
            BucketListFields bucketListFields = new BucketListFields(items);
            startItemsRecyclerView(items);
        } catch (Exception e) {
            Log.d("Data Error :", e.getMessage());
        }

        newItems = findViewById(R.id.floatingItemButton);
        newItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Adds new Item", Toast.LENGTH_SHORT).show();

                String bucketListId = (String) extras.get("bucketListId");


                Intent intent = new Intent(context, AddItemActivity.class);

                intent.putExtra("id", bucketListId);
                startActivity(intent);

            }
        });

    }

    private void startItemsRecyclerView(ArrayList items) {


        RecyclerView recyclerView = findViewById(R.id.itemsList);

        ItemsListCutomAdapter adapter = new ItemsListCutomAdapter(this, items);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


}
