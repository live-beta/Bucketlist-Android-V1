package com.example.sam.bucketlist.views.bucketlists;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.databinding.BucketlistViewAdapterBinding;
import com.example.sam.bucketlist.models.BucketListFields;
import com.example.sam.bucketlist.viewmodel.ItemBucketListViewModel;

import java.util.ArrayList;

public class BucketListAdapter extends RecyclerView.
        Adapter<BucketListAdapter.BucketListAdapterViewHolder> {

    private ArrayList<BucketListFields> bucketlistData;
    private Context context;


    public BucketListAdapter(Context context) {
        this.bucketlistData = new ArrayList<>();
        this.context = context;
    }

    @Override
    public BucketListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BucketlistViewAdapterBinding bucketlistViewAdapterBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.bucketlist_view_adapter, parent,
                        false);
        return new BucketListAdapterViewHolder(bucketlistViewAdapterBinding, context);
    }

    @Override
    public void onBindViewHolder(BucketListAdapterViewHolder holder, int position) {
        holder.bindBucketList(bucketlistData.get(position));
    }

    @Override
    public int getItemCount() {

        return bucketlistData.size();
    }

    public void setBucketList(ArrayList<BucketListFields> bucketList) {
        this.bucketlistData = bucketList;
        notifyDataSetChanged();
    }

    public static class BucketListAdapterViewHolder extends RecyclerView.ViewHolder {

        BucketlistViewAdapterBinding mbucketlistViewAdapterBinding;
        Context context;

        private BucketListAdapterViewHolder(BucketlistViewAdapterBinding bucketlistViewAdapterBinding, Context context) {
            super(bucketlistViewAdapterBinding.bucketData);
            this.mbucketlistViewAdapterBinding = bucketlistViewAdapterBinding;
            this.context = context;
        }

        void bindBucketList(BucketListFields bucketListFields) {
            if (mbucketlistViewAdapterBinding.getMainViewModel() == null) {
                mbucketlistViewAdapterBinding.setMainViewModel(new ItemBucketListViewModel(bucketListFields, itemView.getContext()));

            } else {
                mbucketlistViewAdapterBinding.getMainViewModel().setBucketList(bucketListFields);
            }

        }

    }

}
