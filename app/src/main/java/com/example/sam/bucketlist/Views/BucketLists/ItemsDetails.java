package com.example.sam.bucketlist.Views.BucketLists;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.BucketListMethods.BucketList;
import com.example.sam.bucketlist.Fields.ItemFields;
import com.example.sam.bucketlist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 11/7/17.
 */

public class ItemsDetails extends Activity {

    TextView bucketListID, bucketListName,newBucketListName;
    Button editBucketlist, deleteBucketlist, addItem,editItem,itemDetails;
    ListView itemsDisplay;
    EditText addItemName;
    BucketList bucketList = new BucketList();
    BucketListAPICalls apiCalls = new BucketListAPICalls();
    private final Context context = this;
    ItemFields itemFields = new ItemFields();

    @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_item_details);

      // get the items

        final Bundle extras = getIntent().getExtras();

        HashMap items = new HashMap();

        ArrayList<HashMap> itemsData = itemFields.getItems();

        Log.d("Items", String.valueOf(itemsData));





//        items.put("Id", extras.get("Id"));
//        items.put("Name", extras.get("Name"));
//        items.put("items", extras.get("items"));

        // Convert items into a JSON Object

//
//        try {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                JSONObject itemsObject = new JSONObject(extras.get("items").toString());
//
//                // check items object to deteming the looping patterm
//
//                Log.d("Items Object",String.valueOf(itemsObject));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        itemsData.add(items);
        startItemsRecyclerView(itemsData);
//
//        bucketListID = (TextView)findViewById(R.id.bucketlistViewID);
//        bucketListName =(TextView)findViewById(R.id.bucketlistViewName);
//        newBucketListName =(TextView)findViewById(R.id.newBucketlistName);
//        deleteBucketlist =(Button)findViewById(R.id.bucketlist_delete);
//        editBucketlist = (Button)findViewById(R.id.editBucketlistName);
//        addItem = (Button)findViewById(R.id.addItem);
//        editItem =(Button)findViewById(R.id.editItem);
//        addItemName =(EditText)findViewById(R.id.addItemName);
//        itemsDisplay =(ListView)findViewById(R.id.bucketlistItemList);
//        itemDetails = (Button)findViewById(R.id.showItems);
//
//        final Bundle extras = getIntent().getExtras();
//
//        bucketListID.setText(extras.getString("Id"));
//        bucketListName.setText(extras.getString("Name"));
//
//
//        editBucketlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String newBucketListNameEntry = newBucketListName.getText().toString();
//
//                try{
//
//                    int bucketListID = Integer.parseInt(extras.getString("Id"));
//                    boolean status = apiCalls.editBucketList(bucketListID, newBucketListNameEntry);
//
//                    if (status==true){
//                        Toast.makeText(getApplicationContext(),"Edited Successfully",Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(),"Not a successfull edit",Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    Log.d("HTTP put Error",e.getMessage());
//                }
//
//
//            }
//        });
//
//        deleteBucketlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                try {
//                    int bucketlistID = Integer.parseInt(extras.getString("Id"));
//                    boolean deleteStatus = apiCalls.deleteBucketList(bucketlistID);
//
//                    if (deleteStatus == true){
//
//                        Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(context,BucketlistHome.class);
//                        startActivity(intent);
//
//                    }else {
//                        Toast.makeText(getApplicationContext(),"Delete Failure",Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    Log.d("Error Deleting", e.getMessage());
//                }
//
//
//
//            }
//        });
//
//        addItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//
//                    String newItemName = addItemName.getText().toString();
//
//                    boolean addItemStatus = apiCalls.createItem(Integer.parseInt(extras.
//                            getString("Id")),newItemName);
//
//                    if (addItemStatus == true ){
//                        Toast.makeText(getApplicationContext(),"Item Added",
//                                Toast.LENGTH_SHORT).show();
//
//                    }else {
//                        Toast.makeText(getApplicationContext(),"Item could not be added",
//                                Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                } catch (Exception e){
//                    Log.d("Error Message",e.getMessage());
//                }
//            }
//        });
//
//        itemDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ArrayList<HashMap> itemsData = null;
//                try {
//                    itemsData = bucketList.getSpecifiedBucketList(Integer.
//                            parseInt(extras.getString("Id")));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
//
//                ItemsListAdapter adapter = new ItemsListAdapter(ItemsDetails.this, itemsData);
//                itemsDisplay.setAdapter(adapter);
//
//            }
//        });
//
//
//
//
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
