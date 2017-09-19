package com.example.sam.bucketlist;

import java.util.Date;

/**
 * The class sets and gets Item fields
 */

public class itemFields {
    private int itemID,bucketId;
    private String itemName;
    private Date dateCreated;
    private boolean status;

    public void setItemID(int itemIDEntry){
        this.itemID = itemIDEntry;

    }
    public void setItemName(String itemNameEntry){
        this.itemName = itemNameEntry;
    }
    public void setDateCreated(Date dateCreatedEntry){
        this.dateCreated = dateCreatedEntry;

    }
    public void setStatus(boolean statusEntry){
        this.status = statusEntry;
    }
    public void setBucketId(int bucketIdEntry){
        this.bucketId =bucketIdEntry;
    }

    public int getItemID(){
        return itemID;
    }
    public  int getBucketId(){
        return bucketId;
    }
    public String getItemName(){
        return itemName;
    }
    public Date getDateCreated(){
        return dateCreated;
    }
    public boolean getStatus(){
        return status;
    }

}
