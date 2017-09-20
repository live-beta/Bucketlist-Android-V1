package com.example.sam.bucketlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sam on 9/20/17.
 */

public class BucketListItems {

    ItemFields item = new ItemFields();

    public BucketListItems(){

    }

    public BucketListItems(int itemId, String itemName, Date dateCreated, boolean status, int bucketId){
        item.setBucketId(itemId);
        item.setItemID(bucketId);
        item.setItemName(itemName);
        item.setDateCreated(dateCreated);
        item.setStatus(status);
    }

    public List<ItemFields> createItem(){

        List<ItemFields> itemList = new ArrayList<>();
        itemList.add(item);

        return itemList;
    }
}
