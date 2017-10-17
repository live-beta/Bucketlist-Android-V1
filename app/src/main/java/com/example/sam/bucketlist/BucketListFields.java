package com.example.sam.bucketlist;

import java.util.Date;
import java.util.List;

/**
 * Created by sam on 9/19/17.
 */

public class BucketListFields {

    private int id,userId;
    private String bucketListName;
    private Date dateCreated, dateModified;
    private List<ItemFields> items;

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

    public void setItems(List<ItemFields> itemEntry){

        this.items = itemEntry;
    }
    public List<ItemFields> getItems(){
        return items;
    }

    public String getBucketListName(){
        return bucketListName;
    }


}
