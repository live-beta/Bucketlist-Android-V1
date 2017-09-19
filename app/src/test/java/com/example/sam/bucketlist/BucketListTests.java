package com.example.sam.bucketlist;

import android.util.Log;

import com.example.sam.bucketlist.BucketList;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.junit.Assert.*;
/**
 * Created by sam on 9/19/17.
 */

public class BucketListTests {

    Date date =new Date();

    BucketListItems item = new BucketListItems(1, "Swimming", date ,false , 1);

    List<ItemFields> newItem = item.createItem();

    BucketList bucketList =new BucketList(1,"HAWAII",date, date, 1, newItem );

    @Test
    public void test_that_a_new_bucket_list_can_be_added_successfully(){

        List<BucketListFields> value = bucketList.createBucketList() ;
        assertEquals(value.get(0).getBucketListName(),"HAWAII");

    }
}
