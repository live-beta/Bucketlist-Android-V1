package com.example.sam.bucketlist.views.bucketlists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;

import org.json.JSONException;

/**
 * Created by sam on 12/6/17.
 */

public class AddBucketList extends Activity {

    Button addBucketList;
    EditText newBucketList;

    private APIManager apiManager = new APIManager(getApplicationContext());
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bucketlist);

        addBucketList = findViewById(R.id.addBucketlist);
        newBucketList = findViewById(R.id.newBucketList);


        addBucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBucketlist = newBucketList.getText().toString();

                try {
                    apiManager.addBucketList(newBucketlist);

                    Intent intent = new Intent(context, BucketlistActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
