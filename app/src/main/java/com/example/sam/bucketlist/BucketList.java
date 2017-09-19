package com.example.sam.bucketlist;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Class containing all BucketList Operations
 */

public class BucketList {

    Collection <List<BucketListFields>> allBucketLists = new ArrayList<>();
    List<BucketListFields> bucketList = new ArrayList<>();

    BucketListFields bucketListFields = new BucketListFields();
    Date date = new Date();

    public void BucketList(int id,String bucketListName,Date dateCreated, Date dateModified, int userId, Items items){

        bucketListFields.setId(id);
        bucketListFields.setBucketListName(bucketListName);
        bucketListFields.setDateCreated(dateCreated);
        bucketListFields.setDateModified(dateModified);
        bucketListFields.setUserId(userId);
        bucketListFields.setItems(items);

    }

    public Collection<List<BucketListFields>> createBucketList(){

        bucketList.add(bucketListFields);
        allBucketLists.add(bucketList);

            return allBucketLists;
        }

}
