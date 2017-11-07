package com.example.sam.bucketlist.Views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.QuickContactBadge;
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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class BucketlistHome extends AppCompatActivity{

    Button loadToken, addBucketList,editBucketlist;
    BucketListAPICalls apiCalls = new BucketListAPICalls();
    private JSONObject bucketlistData;
    private EditText newBucketList,editBucketlistEntry;
    private  ListView bucketLister;
//    private ArrayAdapter<String> adapter;
    public BucketListFields bucketListFields = new BucketListFields();
    private Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        loadToken = (Button)findViewById(R.id.getBucketlists);
        bucketLister =(ListView)findViewById(R.id.bucketlister);
        newBucketList =(EditText)findViewById(R.id.addBucketListEntry);
        addBucketList =(Button)findViewById(R.id.addBucketList);
        editBucketlist= (Button)findViewById(R.id.editBucketlist);
        editBucketlistEntry =(EditText)findViewById(R.id.editBucketlistEntry);


        editBucketlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(),"This is edit logic",
                        Toast.LENGTH_SHORT).show();
            }
        });


        loadToken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                BucketList bucketList = new BucketList();

                ArrayList<HashMap> bucketListData = bucketList.getBucketLists();


               // Log.d("Items needed", String.valueOf(bucketListName));



//                adapter = new ArrayAdapter<String>(getApplication(),
//                        android.R.layout.simple_list_item_1);

                /**
                 * TODO
                 * Set up the data types passed to work with the arraylist
                 */

                CustomBucketListAdapter adapter = new CustomBucketListAdapter(BucketlistHome.this,
                        bucketListData);


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
        bucketLister.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                HashMap<String,String> bucketListName =(HashMap<String, String>) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),"I am "+
                        bucketListName,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,ItemsDetails.class);


                intent.putExtra("Name",bucketListName.get("name"));

                startActivity(intent);



            }
        });
    }



    }


