package com.example.sam.bucketlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sam.bucketlist.api.BucketListAPICalls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 19/03/2017.
 */

public class BucketlistHome extends AppCompatActivity{
    private Button loadToken, addBucketList;
    private TextView blists;
    BucketListAPICalls apiCalls = new BucketListAPICalls();
    public static String token;
    final Context context = this;
    private JSONObject bucketlistData;
    private  ListView bucketLister;
    private ArrayAdapter<String> adapter;
    private ArrayList <String>bucketListName =new ArrayList<String>();
    List<BucketListFields> newBucketList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        loadToken = (Button)findViewById(R.id.tokener);
        bucketLister =(ListView)findViewById(R.id.bucketlister);

        loadToken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JSONArray myArrayObject = apiCalls.getBucketLists();

                for (int index = 0; index < myArrayObject.length() ; index++) {

                    try {
                        bucketlistData = new JSONObject( myArrayObject.getString(index));
                        bucketListName.add(bucketlistData.getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new ArrayAdapter<String>(getApplication(),
                        android.R.layout.simple_list_item_1, bucketListName);

                bucketLister.setAdapter(adapter);

            }
        });



        addBucketList = (Button) findViewById(R.id.addBucketList);
        addBucketList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //boolean bucketlistAdded =apiCalls.createBucketList();

            }
        });



    }
}


