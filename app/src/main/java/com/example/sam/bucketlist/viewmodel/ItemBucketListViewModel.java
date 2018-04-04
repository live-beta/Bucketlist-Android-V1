package com.example.sam.bucketlist.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.example.sam.bucketlist.model.BucketListFields;

/**
 * Created by sam on 4/4/18.
 */

public class ItemBucketListViewModel extends BaseObservable {

    private BucketListFields bucketListFields;
    private Context context;

    public ItemBucketListViewModel(BucketListFields bucketListFields, Context context){
        this.bucketListFields = bucketListFields;
        this.context = context;
    }
    public String getBucketListName (){
        return bucketListFields.name;
    }

    public void setBucketList(BucketListFields bucketListFields){
        this.bucketListFields = bucketListFields;
        notifyChange();
    }
}
