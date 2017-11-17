package com.example.sam.bucketlist.Views.BucketListItems;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.Fields.ItemFields;
import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.Views.BucketLists.ItemsDetails;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * the class extends a recycleview --> has an Adapter method of type custom (local, overriden method)--> to
 * enable recycling..
 */

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.BucketListViewAdapter> {

    private LayoutInflater layoutInflator;
    private  ArrayList<HashMap> bucketlistData;



    /**
     *
     * Constructor with required parameters  Context and Data
     */


    public BucketListAdapter(Context context, ArrayList<HashMap> bucketLists){

        this.bucketlistData = bucketLists;
        this.layoutInflator  = LayoutInflater.from(context);
    }

    /**
     *
     * Creating a view holder of custom type.
     * The view holder contains an adapter(with references to the view group)
     * and a data setter(setting data behaviour with widgets)
     */
    @Override
    public BucketListViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflator.inflate(R.layout.bucketlist_view_adapter, parent, false);

        /**
         * The adapter recalls itself as a new instance --> Recycling
         */

        BucketListViewAdapter viewHolder = new BucketListViewAdapter(view);
        return viewHolder;
    }

    /**
     *
     * Data Binder that calls adapter data setter, depending on the data type
     */

    @Override
    public void onBindViewHolder(BucketListViewAdapter holder, int position) {

        HashMap currentDataObject = bucketlistData.get(position);
        holder.setData(currentDataObject,position);

    }

    @Override
    public int getItemCount() {
        return bucketlistData.size();
    }


    /**
     * the recalled class.
     */

    class BucketListViewAdapter extends RecyclerView.ViewHolder{
        TextView id,bucketListName;

        ImageView itemsList, deleteBucketlist;
        int position;


        public BucketListViewAdapter(View bucketlistView){
            super(bucketlistView);

            id =(TextView)bucketlistView.findViewById(R.id.idView);
            bucketListName =(TextView)bucketlistView.findViewById(R.id.bucketlistName);
            itemsList =(ImageView) bucketlistView.findViewById(R.id.viewItems);
            deleteBucketlist = (ImageView) bucketlistView.findViewById(R.id.deleteBucketlist);

        }

        public void setData(final HashMap current, final int position){

            final ItemFields itemFields = new ItemFields();

            final Context context = layoutInflator.getContext();
            final BucketListAPICalls apiCalls = new BucketListAPICalls();
            final HashMap itemCollection = new HashMap();

            this.id.setText(current.get("id").toString());
            this.bucketListName.setText(current.get("name").toString());
            this.itemsList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ItemsDetails.class);

                    intent.putExtra("Name", current.get("name").toString());
                    intent.putExtra("Id", current.get("id").toString());


                    try {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            JSONArray itemsArray = new JSONArray(current.get("items"));

                            Log.d("Items Array", String.valueOf(itemsArray));

                            for (int index = 0; index <itemsArray.length() ; index++) {
                               // itemCollection.put("ItemName", itemsArray);

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    itemCollection.put("items", current.get("items"));


//                    itemFields.setBucketListItem(itemCollection);

                    context.startActivity(intent);

                }
            });

            deleteBucketlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int bucketlistID = Integer.parseInt(current.get("id").toString());
                        boolean deleteStatus = apiCalls.deleteBucketList(bucketlistID);

                        if (deleteStatus == true){

                            Toast.makeText(context,"Delete Success",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context,BucketlistHome.class);
                            context.startActivity(intent);

                        }else {
                            Toast.makeText(context,"Delete Failure",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Log.d("Error Deleting", e.getMessage());
                    }
                }
            });

            this.position = position;
        }

    }

}
