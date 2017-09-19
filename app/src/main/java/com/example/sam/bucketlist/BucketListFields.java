package com.example.sam.bucketlist;

import java.util.Date;

/**
 * Created by sam on 9/19/17.
 */

public class BucketListFields {

    private int id,userId;
    private String bucketListName;
    private Date dateCreated, dateModified;
    private Items items;

    public void setId(int idEntry){
        this.id =idEntry;
    }
    public int getId(){
        return id;
    }
    public void setUserId(int userIdEntry){
        this.userId = userIdEntry;
    }
    public int getUserId(){
        return userId;
    }
    public void setBucketListName(String bucketListNameEntry){
        this.bucketListName = bucketListNameEntry;
    }
    public String getBucketListName(){
        return bucketListName;
    }
    public void setDateCreated(Date dateCreatedEntry){
        this.dateCreated = dateCreatedEntry;

    }
    public Date getDateCreated(){
        return dateCreated;
    }

    public void setDateModified(Date dateModifiedEntry){
        this.dateModified = dateModifiedEntry;
    }
    public Date getDateModified(){
        return dateModified;
    }

    public void setItems(Items itemEntry){
        this.items = itemEntry;
    }
    public Items getItems(){
        return items;
    }


}
