package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.models.ItemFields;
import com.example.sam.bucketlist.views.items.ItemsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BucketListAdapter extends RecyclerView.
        Adapter<BucketListAdapter.BucketListViewAdapter> {

    private LayoutInflater layoutInflator;
    private ArrayList<BucketListFields> bucketlistData;
    private Context context;


    public BucketListAdapter(Context context, ArrayList<BucketListFields> bucketLists) {

        this.bucketlistData = bucketLists;
        this.layoutInflator = LayoutInflater.from(context);

        this.context = context;

    }

    @Override
    public BucketListViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflator.inflate(R.layout.bucketlist_view_adapter, parent,
                false);

        BucketListViewAdapter viewHolder = new BucketListViewAdapter(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BucketListViewAdapter holder, int position) {

        BucketListFields currentDataObject = bucketlistData.get(position);
        holder.setData(currentDataObject, position);

    }

    @Override
    public int getItemCount() {

        return bucketlistData.size();
    }

    class BucketListViewAdapter extends RecyclerView.ViewHolder {
        TextView id, bucketListName;

        ImageView itemsList, deleteBucketlist;
        int position;
        ArrayList  itemNames = new ArrayList();


        public BucketListViewAdapter(View bucketlistView) {
            super(bucketlistView);

            id = bucketlistView.findViewById(R.id.idView);
            bucketListName = bucketlistView.findViewById(R.id.bucketlistName);
            itemsList = bucketlistView.findViewById(R.id.viewItems);
            deleteBucketlist = bucketlistView.findViewById(R.id.deleteBucketlist);

        }

        public void setData(final BucketListFields current, final int position) {

            this.id.setText(current.getId());

            this.bucketListName.setText(current.getBucketListName());
            this.itemsList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ItemsActivity.class);

                    Log.d("Size of this", String.valueOf(current.getItems().size()));

                    if (current.getItems().size() == 0) {

                        Toast.makeText(context, "No Items to show", Toast.LENGTH_LONG).show();
                    } else {

                        for (int index = 0; index < current.getItems().size(); index++) {

                            String name = current.getItems().get(index).getName();
                            itemNames.add(name);


                        }

                        intent.putExtra("items", itemNames);
                        intent.putExtra("bucketListId", current.getId());
                        context.startActivity(intent);

                    }
                }
            });

            deleteBucketlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, BucketlistActivity.class);
                    APIManager apiManager = new APIManager();
                    SharedPreferences sharedPreferences = PreferenceManager.
                            getDefaultSharedPreferences(context);
                    apiManager.deleteBucketList(sharedPreferences.
                            getString("token", ""), current.getId(), context);

                    context.startActivity(intent);

                }
            });

            this.position = position;
        }

    }

}
