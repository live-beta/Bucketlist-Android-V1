package com.example.sam.bucketlist.API;

import android.os.StrictMode;
import android.util.Log;

import com.example.sam.bucketlist.Fields.BucketListFields;
import com.example.sam.bucketlist.Fields.ItemFields;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Implements BucketList API calls
 */

public class BucketListAPICalls {

    public static String  token;

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }


    public boolean login(String userName, String password){

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if (userName.equals("") || password.equals("")){
            Log.d("Value", " User name or password cannot be empty");
            return false;
        } else {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/auth/login");
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("username",userName));
            nameValuePair.add(new BasicNameValuePair("password",password));

            try{
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            }catch(UnsupportedEncodingException e){

                e.printStackTrace();
            }
            try {
                HttpResponse response = httpClient.execute(httpPost);
                String responseString = EntityUtils.toString(response.getEntity());

                if (responseString.contains("Could not log you in, Check credentials")){
                    return false;
                }

                JSONObject jsonResponse = new JSONObject(responseString);
                String token = jsonResponse.getString("token");

                setToken(token);

                return true;


            }catch (ClientProtocolException e){
                Log.d("Error", e.getMessage());
            }catch (IOException e){

                e.printStackTrace();

            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    public JSONArray getBucketLists() {

        JSONArray jsonArray = new JSONArray();

        token = getToken();

        Log.d("BucketList Token", token.toString());

        if (token.equals("")) {
            Log.d("Auth ", "Not Authorized, Login in again");
        } else {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://10.0.2.2:5000/api/v1/bucketlists");
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("Authorization","Bearer " + token);

            try {
                HttpResponse response = httpClient.execute(httpGet);
                String responseStr = EntityUtils.toString(response.getEntity());

                jsonArray = new JSONArray(responseStr);


            } catch (ClientProtocolException e) {

            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonArray;
    }


    public boolean createBucketList(String name){

        token = getToken();
        if (token.equals("")){
            Log.d("Auth ", "Not Authorized, Login in again");
            return false;
        } else {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/bucketlists");
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
            httpPost.addHeader("Authorization","Bearer "+token);

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("name", name));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPost);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200){
                    return true;
                }else{
                    return false;
                }


            } catch (ClientProtocolException e) {
                return false;

            } catch (IOException e) {
                Log.d("Exception",e.getLocalizedMessage());

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

                /*
                * TODO
                * Debug API HTTP Responses */

                if (responseCode == 200){
                    return true;
                }else if (responseCode == 201){
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
