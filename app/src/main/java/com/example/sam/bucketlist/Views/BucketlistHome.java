package com.example.sam.bucketlist.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.BucketListMethods.BucketList;
import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sam on 19/03/2017.
 */

public class BucketlistHome extends AppCompatActivity{

    private Button loadToken, addBucketList;
    BucketListAPICalls apiCalls = new BucketListAPICalls();
    private JSONObject bucketlistData;
    private EditText newBucketList;
    private  ListView bucketLister;
    private ArrayAdapter<String> adapter;
    public BucketListFields bucketListFields = new BucketListFields();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        loadToken = (Button)findViewById(R.id.tokener);
        bucketLister =(ListView)findViewById(R.id.bucketlister);
        newBucketList =(EditText)findViewById(R.id.bucketlistName);
        addBucketList =(Button)findViewById(R.id.addBucketList);

        loadToken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                BucketList bucketList = new BucketList();

                ArrayList <String> bucketListName = bucketList.getBucketLists();

                adapter = new ArrayAdapter<String>(getApplication(),
                        android.R.layout.simple_list_item_1, bucketListName);

                bucketLister.setAdapter(adapter);

            }
        });

        addBucketList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String newBucketListInputName = newBucketList.getText().toString();

                boolean status = false;

                try {
                  status = apiCalls.createBucketList(newBucketListInputName);
                    Toast.makeText(getApplicationContext(),"Status Value!"+ status,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error:" + e.getMessage(),Toast.LENGTH_LONG).show();
                }


                if (status == true){
                    Toast.makeText(getApplicationContext(),"BucketList Created!",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"oops, that didn't work",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}


