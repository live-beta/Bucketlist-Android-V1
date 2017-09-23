package com.example.sam.bucketlist;

import android.content.ContentProvider;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class contains Application Programming Interface calls for bucketlist functions.
 */

public class BucketListAPICalls {

    private String  token;
    private JSONObject jsonResponseObject;
    private  List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }


    public boolean login(String username, String password){

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

    public JSONObject getBucketLists() {

        token = getToken();
        if (token.equals("")) {
            Log.d("Auth ", "Not Authorized, Login in again");
        } else {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://10.0.2.2:5000/api/v1/bucketlist");

            try {
                HttpResponse response = httpClient.execute(httpGet);
                String responseStr = EntityUtils.toString(response.getEntity());
                jsonResponseObject= new JSONObject(responseStr);

                if (!token.isEmpty()) {
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonResponseObject;
    }


    public boolean createBucketList(List<BucketListFields> newBucketList){
        token = getToken();
        String name = newBucketList.get(0).getBucketListName();
        List<ItemFields> items= newBucketList.get(0).getItems();

        if (token.equals("")){
            Log.d("Auth ", "Not Authorized, Login in again");
            return false;
        } else {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/bucketlists");

            try {

                httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
                httpPost.addHeader("Authorization","Bearer "+token);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("name", name));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPost);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200){
                    return true;
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }

        }

        return false;
    }
    public boolean editBucketList(int id, String newName){

        if (token.equals("")){
            Log.d("Auth : ", "Not Authorized");
            return false;
        }else {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut("http://10.0.2.2:5000/api/v1/bucketlists/"+id);

            try {

                httpPut.addHeader("Content-Type","application/x-www-form-urlencoded");
                httpPut.addHeader("Authorization","Bearer "+token);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("name", newName));
                httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPut);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200){
                    return true;
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }

        }

     return false;
    }
    public boolean deleteBucketList(int id){
        if (token.equals("")){
            Log.d("Auth : ", "Not Authorized");
            return false;
        }else {
            HttpClient httpclient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete("http://10.0.2.2:5000/api/v1/bucketlists/"+id);

            try {

                httpDelete.addHeader("Content-Type","application/x-www-form-urlencoded");
                httpDelete.addHeader("Authorization","Bearer "+token);
                HttpResponse response = httpclient.execute(httpDelete);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200){
                    return true;
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }

        }
        return false;
    }
    public boolean createItem(List<ItemFields> newItems) {
        token = getToken();
        String name = newItems.get(0).getItemName();

        if (token.equals("")) {
            Log.d("Auth ", "Not Authorized, Login in again");
            return false;
        } else {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/items");

            try {

                httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                httpPost.addHeader("Authorization", "Bearer " + token);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("status", "false"));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPost);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200) {
                    return true;
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return false;
        }
    }
    public boolean editItem(int id,String newItemName){
        if (token.equals("")){
            Log.d("Auth : ", "Not Authorized");
            return false;
        }else {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut("http://10.0.2.2:5000/api/v1/items/"+id);

            try {

                httpPut.addHeader("Content-Type","application/x-www-form-urlencoded");
                httpPut.addHeader("Authorization","Bearer "+token);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("name", newItemName));
                httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPut);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200){
                    return true;
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }

        }

        return false;
    }

}
