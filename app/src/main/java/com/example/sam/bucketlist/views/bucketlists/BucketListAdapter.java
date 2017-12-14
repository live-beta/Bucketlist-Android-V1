package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.BucketListFields;

import java.util.ArrayList;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.BucketListViewAdapter> {

    private LayoutInflater layoutInflator;
    private  ArrayList<BucketListFields> bucketlistData;


    public BucketListAdapter(Context context, ArrayList<BucketListFields> bucketLists){

        this.bucketlistData = bucketLists;
        this.layoutInflator  = LayoutInflater.from(context);
    }

    @Override
    public BucketListViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflator.inflate(R.layout.bucketlist_view_adapter, parent, false);

        BucketListViewAdapter viewHolder = new BucketListViewAdapter(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BucketListViewAdapter holder, int position) {

        BucketListFields currentDataObject = bucketlistData.get(position);
        holder.setData(currentDataObject,position);

    }

    @Override
    public int getItemCount() {
        return bucketlistData.size();
    }

    class BucketListViewAdapter extends RecyclerView.ViewHolder{
        TextView id,bucketListName;

        ImageView itemsList, deleteBucketlist;
        int position;


        public BucketListViewAdapter(View bucketlistView){
            super(bucketlistView);

            id = bucketlistView.findViewById(R.id.idView);
            bucketListName = bucketlistView.findViewById(R.id.bucketlistName);
            itemsList = bucketlistView.findViewById(R.id.viewItems);
            deleteBucketlist = bucketlistView.findViewById(R.id.deleteBucketlist);

        }

        public void setData(final BucketListFields current, final int position){

            this.id.setText(current.getId());
            this.bucketListName.setText(current.getBucketListName());
            this.itemsList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                }
            });

            deleteBucketlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            this.position = position;
        }

    }

}
