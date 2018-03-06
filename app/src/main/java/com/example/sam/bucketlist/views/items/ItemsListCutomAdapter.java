package com.example.sam.bucketlist.views.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.models.datamodels.ItemFields;

import java.util.ArrayList;

/**
 * Creates a custom recycler view for data items
 */

public class ItemsListCutomAdapter extends RecyclerView.Adapter<ItemsListCutomAdapter.CustomViewAdapter> {


    private LayoutInflater layoutInflater;
    private ArrayList<ItemFields> data;


    public ItemsListCutomAdapter(Context context, ArrayList<ItemFields> data) {

        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public CustomViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.items_view_adapter, parent, false);

        CustomViewAdapter viewHolder = new CustomViewAdapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewAdapter holder, int position) {

        String itemInfoName = String.valueOf(data.get(position).getName());

        holder.setData(itemInfoName, position);
    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    class CustomViewAdapter extends RecyclerView.ViewHolder {

        TextView id, itemName;
        ImageView deleteBucketlist;

        public CustomViewAdapter(View itemsView) {
            super(itemsView);

            id = itemsView.findViewById(R.id.idView);
            itemName = itemsView.findViewById(R.id.itemName);
            deleteBucketlist = itemsView.findViewById(R.id.deleteBucketlist);

        }

        public void setData(final String itemInfoName, final int position) {

            String number = String.valueOf(position + 1);
            id.setText(number);
            itemName.setText(itemInfoName);
            deleteBucketlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }

}
