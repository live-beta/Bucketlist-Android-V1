package com.example.sam.bucketlist.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sam.bucketlist.R;

import org.w3c.dom.Text;

/**
 * Created by sam on 11/7/17.
 */

public class ItemsDetails extends AppCompatActivity {

    TextView bucketListID, bucketListName,newBucketListName;
    Button editBucketlist;
    ListView itemsDisplay;

    @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        bucketListID = (TextView)findViewById(R.id.bucketlistViewID);
        bucketListName =(TextView)findViewById(R.id.bucketlistViewName);
        newBucketListName =(TextView)findViewById(R.id.newBucketlistName);
        editBucketlist = (Button)findViewById(R.id.editBucketlistName);
        itemsDisplay =(ListView)findViewById(R.id.bucketlistItemList);


    }
}
