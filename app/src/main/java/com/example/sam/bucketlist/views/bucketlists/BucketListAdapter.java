package com.example.sam.bucketlist.views.bucketLists;

import android.content.Context;
import android.content.Intent;
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
import com.example.sam.bucketlist.models.DeletePost;
import com.example.sam.bucketlist.views.items.ItemsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void deleteBucketlists(String id, final Context context) {

        APIManager apiManager = new APIManager(context);
        Call<DeletePost> call = apiManager.deleteBucketList(id);
        call.enqueue(new Callback<DeletePost>() {
            @Override
            public void onResponse(Call<DeletePost> call, Response<DeletePost> response) {
                Toast.makeText(context, "Deleted " + response.message(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DeletePost> call, Throwable t) {

                Toast.makeText(context, "Could not Delete " + t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    class BucketListViewAdapter extends RecyclerView.ViewHolder {
        TextView id, bucketListName, bucketListDescription;

        ImageView itemsList, deleteBucketlist;
        int position;
        ArrayList itemNames = new ArrayList();


        public BucketListViewAdapter(View bucketlistView) {
            super(bucketlistView);

            id = bucketlistView.findViewById(R.id.idView);
            bucketListName = bucketlistView.findViewById(R.id.bucketlistName);
            bucketListDescription = bucketlistView.findViewById(R.id.description);
            itemsList = bucketlistView.findViewById(R.id.viewItems);
            deleteBucketlist = bucketlistView.findViewById(R.id.deleteBucketlist);

        }

        public void setData(final BucketListFields current, final int position) {

            String numberLabel = String.valueOf(position + 1);
            this.id.setText(numberLabel);

            this.bucketListName.setText(current.getBucketListName());
            this.bucketListDescription.setText(current.getBucketListDescription());

            Log.d("this is descript ", current.getBucketListDescription().toString());

            this.itemsList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ItemsActivity.class);

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

                    Log.d("Current ID", current.getId());

                    deleteBucketlists(current.getId(), context);

                    Log.d("Value of id", current.getId());
                    context.startActivity(intent);

                }
            });

            this.position = position;
        }


    }

}
