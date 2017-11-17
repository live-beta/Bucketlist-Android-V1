package com.example.sam.bucketlist.Views.BucketLists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 11/15/17.
 */


/**
 * TODO
 *
 * List the items Data
 */
public class ItemsListCutomAdapter extends RecyclerView.Adapter<ItemsListCutomAdapter.CustomViewAdapter>{


    private LayoutInflater layoutInflater;
    private ArrayList<HashMap> data;


    public ItemsListCutomAdapter(Context context, ArrayList<HashMap> itemsData){


        this.data = itemsData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public CustomViewAdapter onCreateViewHolder(ViewGroup parent, int viewType){

        View view =layoutInflater.inflate(R.layout.items_view_adapter,parent,false);

        CustomViewAdapter viewHolder = new CustomViewAdapter(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CustomViewAdapter holder, int position){

        HashMap currentItemsObject  = data.get(position);
        holder.setData(currentItemsObject,position);
    }

    @Override
    public  int getItemCount(){
        return data.size();
    }



class CustomViewAdapter extends RecyclerView.ViewHolder{

    TextView id, itemName;
    ImageView deleteBucketlist;

    public CustomViewAdapter(View itemsView ){
        super(itemsView);

        // get the views

        id = (TextView) itemsView.findViewById(R.id.idView);
        itemName = (TextView) itemsView.findViewById(R.id.itemName);
        deleteBucketlist = (ImageView)itemsView.findViewById(R.id.deleteBucketlist);

    }
    public void setData(final HashMap current,final int position){

        final Context context  = layoutInflater.getContext();

        id.setText(current.get("Id").toString());
        itemName.setText(current.get("items").toString());
        deleteBucketlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context," "+ current.get("items"),Toast.LENGTH_SHORT).show();
//                BucketListAPICalls apiCalls = new BucketListAPICalls();
//                apiCalls.deleteBucketList(Integer.parseInt(current.get("id").toString()));
//                Toast.makeText(context,"Delete Successful",Toast.LENGTH_SHORT).show();
            }
        });

    }

}

}
