package com.example.sam.bucketlist;

import com.example.sam.bucketlist.BucketListMethods.BucketList;
import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.Fields.ItemFields;
import com.example.sam.bucketlist.BucketListMethods.BucketListItems;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by sam on 9/19/17.
 */

public class BucketListTests {

    public static BucketList bucketList;
    public static List<ItemFields> newItem;
    public static BucketListItems item;

    @BeforeClass
    public static void setUp(){
        Date date =new Date();

        item = new BucketListItems(1, "Swimming", date ,false , 1);

        newItem = item.createItem();

        bucketList =new BucketList(1,"HAWAII",date, date, 1, newItem );

    }

    @AfterClass
    public static void tearDown(){

        bucketList = null;
        newItem =null;
        item =null;

    }

    @Test
    public void test_that_a_new_bucket_list_can_be_added_successfully(){

        List<BucketListFields> value = bucketList.createBucketList() ;
        assertEquals(value.get(0).getBucketListName(),"HAWAII");

    }
    @Test
    public void test_that_a_bucket_list_can_be_renamed(){

        assertEquals(bucketList.renameBucketList("HAWAII","ZANZIBAR"),true);

    }
    @Test
    public void test_that_a_bucket_list_can_be_deleted(){
        assertEquals(bucketList.deleteBucketList("ZANZIBAR"),true);
    }
}
