package com.example.sam.bucketlist.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.R;

import org.w3c.dom.Text;

/**
 * Created by sam on 11/7/17.
 */

public class ItemsDetails extends AppCompatActivity {

    TextView bucketListID, bucketListName,newBucketListName;
    Button editBucketlist;
    ListView itemsDisplay;
    BucketListAPICalls apiCalls = new BucketListAPICalls();

    @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        bucketListID = (TextView)findViewById(R.id.bucketlistViewID);
        bucketListName =(TextView)findViewById(R.id.bucketlistViewName);
        newBucketListName =(TextView)findViewById(R.id.newBucketlistName);
        editBucketlist = (Button)findViewById(R.id.editBucketlistName);
        itemsDisplay =(ListView)findViewById(R.id.bucketlistItemList);

        final Bundle extras = getIntent().getExtras();

        bucketListID.setText(extras.getString("Id"));
        bucketListName.setText(extras.getString("Name"));

        editBucketlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newBucketListNameEntry = newBucketListName.getText().toString();

                try{

                    int bucketListID = Integer.parseInt(extras.getString("Id"));
                    boolean status = apiCalls.editBucketList(bucketListID, newBucketListNameEntry);

                    if (status==true){
                        Toast.makeText(getApplicationContext(),"Edited Successfully",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Not a successfull edit",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("HTTP put Error",e.getMessage());
                }


            }
        });



    }
}
