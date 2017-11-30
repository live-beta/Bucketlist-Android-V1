package com.example.sam.bucketlist.views.items;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sam.bucketlist.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 11/7/17.
 */

public class ItemsActivity extends Activity {


    @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_item_details);

        final Bundle extras = getIntent().getExtras();

        HashMap items = new HashMap();

  }

  private void startItemsRecyclerView(ArrayList<HashMap> items){


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemsList);

        ItemsListCutomAdapter adapter = new ItemsListCutomAdapter(this, items);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        }

}
