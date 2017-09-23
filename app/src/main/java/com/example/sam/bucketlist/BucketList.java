package com.example.sam.bucketlist;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class containing all BucketList Operations
 */

public class BucketList {

    Collection <List<BucketListFields>> allBucketLists = new ArrayList<>();
    List<BucketListFields> bucketList = new ArrayList<>();
    public static String token;

    BucketListFields bucketListFields = new BucketListFields();
    Date date = new Date();

    public BucketList(int id,String bucketListName,Date dateCreated, Date dateModified, int userId, List<ItemFields> items){

        bucketListFields.setId(id);
        bucketListFields.setBucketListName(bucketListName);
        bucketListFields.setDateCreated(dateCreated);
        bucketListFields.setDateModified(dateModified);
        bucketListFields.setUserId(userId);
        bucketListFields.setItems(items);

    }

    public List<BucketListFields> createBucketList(){

        bucketList.add(bucketListFields);
        allBucketLists.add(bucketList);

        return bucketList;
        }
    public boolean renameBucketList(String bucketListName,String newName){

        Iterator<List<BucketListFields>> iterator = allBucketLists.iterator();

        while (iterator.hasNext()){
            List<BucketListFields> listElement = iterator.next();
            if (listElement.get(0).getBucketListName().equals(bucketListName)){
                listElement.get(0).setBucketListName(newName) ;
                return true;

            }
        }
        return false;

    }
    public boolean deleteBucketList(String bucketListName){

        Iterator<List<BucketListFields>> iterator = allBucketLists.iterator();

        while (iterator.hasNext()){
            List<BucketListFields> listElement = iterator.next();
            if (listElement.get(0).getBucketListName().equals(bucketListName)){
                iterator.remove();
            }
            return true;
        }
        return false;
    }


}
