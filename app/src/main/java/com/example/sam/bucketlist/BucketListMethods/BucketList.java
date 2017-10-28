package com.example.sam.bucketlist.BucketListMethods;

import android.widget.Toast;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.Fields.ItemFields;
import com.example.sam.bucketlist.Fields.UserFields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class BucketList Application Operations
 *
 */

public class BucketList {

    Collection <List<BucketListFields>> allBucketLists = new ArrayList<>();
    List<BucketListFields> bucketList = new ArrayList<>();
    public static String token;

    BucketListFields bucketListFields = new BucketListFields();
    UserFields userFields = new UserFields();
    BucketListAPICalls apiCalls = new BucketListAPICalls();

    public BucketList(int id,String bucketListName,Date dateCreated, Date dateModified, int userId, List<ItemFields> items){

        bucketListFields.setId(id);
        bucketListFields.setBucketListName(bucketListName);
        bucketListFields.setDateCreated(dateCreated);
        bucketListFields.setDateModified(dateModified);
        bucketListFields.setUserId(userId);
        bucketListFields.setItems(items);

    }

    public BucketList(String userName,String password){

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);
    }

    public boolean login(){

        boolean status = apiCalls.login(userFields.getUserName(),userFields.getPassword());
        return status;
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
