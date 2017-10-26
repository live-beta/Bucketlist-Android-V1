package com.example.sam.bucketlist;

import com.example.sam.bucketlist.API.BucketListAPICalls;
import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.Fields.ItemFields;

/**
 * Created by sam on 9/23/17.
 */
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class ApiTests {

    private static BucketListAPICalls apicalls ;
    private static BucketListFields newBucketListFields = new BucketListFields();
    private static ItemFields itemFields = new ItemFields();
    private static List<BucketListFields> newBucketList =new ArrayList<>();
    private static List<ItemFields> newItem =new ArrayList<>();
    private static Date date = new Date();

    @BeforeClass
    public static void setUp(){
       apicalls = new BucketListAPICalls();

        itemFields.setBucketId(1);
        itemFields.setDateCreated(date);
        itemFields.setStatus(false);
        itemFields.setBucketId(1);
        itemFields.setItemName("SKY DIVING");
        itemFields.setItemID(1);
        newItem.add(itemFields);

        newBucketListFields.setBucketListName("KHARTOUM");
        newBucketListFields.setUserId(1);
        newBucketListFields.setDateModified(date);
        newBucketListFields.setId(1);
        newBucketListFields.setDateCreated(date);
        newBucketListFields.setItems(newItem);
        newBucketList.add(newBucketListFields);

    }

    @Test
    public void test_that_api_login_works(){
        assertEquals(apicalls.login("swanjala","sp33d1ste"),true);
    }

    @Test
    public void test_that_bucketlist_can_be_added_successfully(){
        assertEquals(apicalls.createBucketList(newBucketList),true);
    }

    @Test
    public void test_that_bucketlist_can_be_edited(){
        assertEquals(apicalls.editBucketList(1,"CHAD"),true);
    }

    @Test
    public void test_that_bucketlist_can_be_deleted(){
        assertEquals(apicalls.deleteBucketList(1),true);
    }

    @Test
    public void test_that_an_item_can_be_created(){
        assertEquals(apicalls.createItem(newItem),true);
    }

    @Test
    public void test_that_an_item_can_be_edited(){
        assertEquals(apicalls.editItem(1,"SWIMMING"),true);
    }


}
