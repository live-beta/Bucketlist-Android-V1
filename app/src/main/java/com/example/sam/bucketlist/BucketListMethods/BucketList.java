package com.example.sam.bucketlist.BucketListMethods;

import android.util.Log;
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
import java.util.Objects;

/**
 * Class BucketList Application Operations
 */

public class BucketList {

    Collection<List<BucketListFields>> allBucketLists = new ArrayList<>();
    List<BucketListFields> bucketList = new ArrayList<>();
    public static String token;

    BucketListFields bucketListFields = new BucketListFields();
    ItemFields itemFields = new ItemFields();
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

    public ArrayList<HashMap> getBucketLists() throws JSONException {

        JSONArray myBucketListsArrayObject = apiCalls.getBucketLists();




        JSONObject bucketlistData;
        JSONArray items;



        ArrayList<HashMap> bucketLists = new ArrayList<HashMap>();

        for (int index = 0; index < myBucketListsArrayObject.length(); index++) {

            try {
                HashMap bucketListData = new HashMap<>();
                bucketlistData = new JSONObject( myBucketListsArrayObject.getString(index));

                items = bucketlistData.getJSONArray("items");
                HashMap itemField =new HashMap();
                ArrayList<HashMap> bucketListItem = new ArrayList<>();

                for (int itemIndex = 0; itemIndex < items.length() ; itemIndex++) {

                    ArrayList data = new ArrayList();



                    JSONObject fields =items.optJSONObject(itemIndex);

                  //  Log.d("All Items ", String.valueOf(fields));

                    data.add(String.valueOf(fields.get("name")));
                    data.add(String.valueOf(fields.get("id")));

                    itemField.put(itemIndex,data);
//                    itemField.put(itemIndex, String.valueOf(fields.get("id")));


//                    Log.d("Item Field", fields.getString("name").
//                            concat(" "+fields.getString("id")));

                }
                bucketListItem.add(itemField);

                bucketListFields.setBucketListName(bucketlistData.getString("name"));
                bucketListFields.setId(bucketlistData.getInt("id"));



                /**
                 * TODO
                 * Trace the bucketlist items data giving its setters and getters.
                 *  Streamline the data streams
                 *
                 *  Work on threading.
                 */

                bucketListData.put("name", bucketListFields.getBucketListName());
                bucketListData.put("id", String.valueOf(bucketListFields.getId()));
                bucketListData.put("items", bucketListItem);



                bucketLists.add(bucketListData);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d("Itemness :-P",String.valueOf(bucketLists));

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

    public ArrayList<HashMap> getSpecifiedBucketList(int bucketListId) throws JSONException, NoSuchFieldException {

        ArrayList<HashMap> items = new ArrayList<>();

        Object itemsObject = apiCalls.getBucketList(bucketListId);



        Log.d("Items Object", String.valueOf(itemsObject));

//        JSONObject itemsData ;
//
//        ArrayList<HashMap> items = new ArrayList<HashMap>();
//
//
//        for (int index = 0; index < itemsObject.length(); index++) {
//
//            try {
//                HashMap<String, String> itemData = new HashMap<>();
//                //bucketlistData = myBucketListsArrayObject.getJSONObject(index);
//                itemsData = new JSONObject( itemsArrayObject.getString(index));
//
//                itemFields.setItemID(itemsData.getInt("id"));
//                itemFields.setItemName(itemsData.getString("name"));
//
//
//                itemData.put("name", itemFields.getItemName());
//                itemData.put("id", String.valueOf(itemFields.getItemID()));
//
//                items.add(itemData);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
////
//
         return items;
      }



}
