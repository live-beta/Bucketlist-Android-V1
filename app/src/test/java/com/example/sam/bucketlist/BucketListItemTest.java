package com.example.sam.bucketlist;

/**
 * TODO
 * Refactor unit tests
 */
//import com.example.sam.bucketlist.BucketListMethods.BucketListItems;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class BucketListItemTest {

   // private static BucketListItems bucketListItems;

    @BeforeClass
    public static void setUp(){
        Date date = new Date();

     // bucketListItems = new BucketListItems(1,"Mountain Climbing",date,false,1);
    }

    @AfterClass
    public static void tearDown(){

    }

    @Test
    public void test_that_an_item_has_been_created_successfully(){

      //  List<ItemFields> item = bucketListItems.createItem();
     //   assertEquals(item.get(0).getItemName(),"Mountain Climbing");
    }

}
