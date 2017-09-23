package com.example.sam.bucketlist;

import android.content.ContentProvider;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 9/23/17.
 */

public class BucketListAPICalls {

    private String  token;

    public boolean login(String username, String password){


        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username",username));
        nameValuePair.add(new BasicNameValuePair("password",password));

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/auth/login");

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return false;
        }

        try{
            HttpResponse response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity());
            Log.d("HTTP Post Response :", responseStr);

            JSONObject resp = new JSONObject(responseStr);
            Log.d("HTTP JSON :", String.valueOf(resp));

            token = resp.getString("token");

            if (!token.isEmpty()){
                return true;
            }

        } catch (ClientProtocolException e){

        } catch (IOException e){
            // Log exception
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createBucketList(List<BucketListFields> newBucketList){


        return false;
    }
    public boolean editBucketList(String newName){
     return false;
    }
    public boolean deleteBucketList(String bucketListName){
        return false;
    }
    public boolean createItem(List<ItemFields> newItems){
        return false;
    }
    public boolean editItem(String newItemName){
        return false;
    }

}
