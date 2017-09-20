package com.example.sam.bucketlist;

/**
 * Created by sam on 9/20/17.
 */
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BucketListItemTest {

    private static BucketListItems bucketListItems;

    @BeforeClass
    public static void setUp(){
        Date date = new Date();

      bucketListItems = new BucketListItems(1,"Mountain Climbing",date,false,1);
    }

    @AfterClass
    public static void tearDown(){

    }

    @Test
    public void test_that_an_item_has_been_created_successfully(){

        List<ItemFields> item = bucketListItems.createItem();
        assertEquals(item.get(0).getItemName(),"Mountain Climbing");
    }

}
