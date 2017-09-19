package com.example.sam.bucketlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Sam on 19/03/2017.
 */

public class Items {

    Collection<List<ItemFields>> allItems = new ArrayList<>();
    List<ItemFields> itemsList = new ArrayList<>();
    ItemFields itemFields = new ItemFields();

    public void Items(int itemId, String itemName, Date dateCreated,boolean status, int bucketId){

        itemFields.setItemID(itemId);
        itemFields.setItemName(itemName);
        itemFields.setDateCreated(dateCreated);
        itemFields.setStatus(status);
        itemFields.setBucketId(bucketId);
    }
    public Collection <List<ItemFields>> createItems(){
        itemsList.add(itemFields);
        allItems.add(itemsList);
        return allItems;
    }
}
