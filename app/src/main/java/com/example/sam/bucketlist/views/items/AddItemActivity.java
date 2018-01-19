package com.example.sam.bucketlist.views.items;

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
import com.example.sam.bucketlist.views.bucketlists.BucketlistActivity;

/**
 * Created by sam on 1/17/18.
 */


public class AddItemActivity extends Activity {

    private EditText itemName;
    private Button addItem;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        itemName = findViewById(R.id.itemName);
        addItem = findViewById(R.id.addItem);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                String token = sharedPreferences.getString("token", "");
                final Bundle extras = getIntent().getExtras();
                String id = (String) extras.get("id");
                APIManager apiManager = new APIManager();

                apiManager.addItem(token, itemName.getText().toString(), id);

                Intent intent = new Intent(context, BucketlistActivity.class);
                startActivity(intent);

            }
        });


    }
}
