package com.example.sam.bucketlist.BucketListMethods;

import android.widget.Toast;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.Fields.ItemFields;
import com.example.sam.bucketlist.Fields.UserFields;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Class BucketList Application Operations
 */

public class BucketList {

    Collection<List<BucketListFields>> allBucketLists = new ArrayList<>();
    List<BucketListFields> bucketList = new ArrayList<>();
    public static String token;

    BucketListFields bucketListFields = new BucketListFields();
    UserFields userFields = new UserFields();
    BucketListAPICalls apiCalls = new BucketListAPICalls();

    public BucketList(int id, String bucketListName, Date dateCreated, Date dateModified, int userId, List<ItemFields> items) {

        bucketListFields.setId(id);
        bucketListFields.setBucketListName(bucketListName);
        bucketListFields.setDateCreated(dateCreated);
        bucketListFields.setDateModified(dateModified);
        bucketListFields.setUserId(userId);
        bucketListFields.setItems(items);

    }

    public BucketList() {

    }

    public BucketList(String userName, String password) {

        this.userFields.setUserName(userName);
        this.userFields.setPassword(password);
    }

    public boolean login() {

        boolean status = apiCalls.login(userFields.getUserName(), userFields.getPassword());
        return status;
    }

    public ArrayList<HashMap> getBucketLists() {

        JSONArray myBucketListsArrayObject = apiCalls.getBucketLists();
        JSONObject bucketlistData;


        ArrayList<HashMap> bucketLists = new ArrayList<HashMap>();

        for (int index = 0; index < myBucketListsArrayObject.length(); index++) {

            try {
                HashMap<String, String> bucketListData = new HashMap<>();
                //bucketlistData = myBucketListsArrayObject.getJSONObject(index);
                bucketlistData = new JSONObject( myBucketListsArrayObject.getString(index));
                bucketListFields.setBucketListName(bucketlistData.getString("name"));
                bucketListFields.setId(bucketlistData.getInt("id"));

                bucketListData.put("name", bucketListFields.getBucketListName());
                bucketListData.put("id", String.valueOf(bucketListFields.getId()));

                bucketLists.add(bucketListData);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return bucketLists;
    }


    public List<BucketListFields> createBucketList() {

        bucketList.add(bucketListFields);
        allBucketLists.add(bucketList);

        return bucketList;
    }

    public boolean renameBucketList(String bucketListName, String newName) {

        Iterator<List<BucketListFields>> iterator = allBucketLists.iterator();

        while (iterator.hasNext()) {
            List<BucketListFields> listElement = iterator.next();
            if (listElement.get(0).getBucketListName().equals(bucketListName)) {
                listElement.get(0).setBucketListName(newName);
                return true;

            }
        }
        return false;

    }

    public boolean deleteBucketList(String bucketListName) {

        Iterator<List<BucketListFields>> iterator = allBucketLists.iterator();

        while (iterator.hasNext()) {
            List<BucketListFields> listElement = iterator.next();
            if (listElement.get(0).getBucketListName().equals(bucketListName)) {
                iterator.remove();
            }
            return true;
        }
        return false;
    }


}
