package com.example.sam.bucketlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sam on 19/03/2017.
 */

public class BucketlistHome extends AppCompatActivity{
    Button loadtoken;
    TextView blists;
    public static String token;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        token= getIntent().getStringExtra("Token");
        loadtoken = (Button) findViewById(R.id.tokener);
        loadtoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This is"+ token,Toast.LENGTH_LONG).show();

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://10.0.2.2:5000/api/v1/bucketlists");
                httpGet.addHeader("Content-Type","application/x-www-form-urlencoded");
                httpGet.addHeader("Authorization","Bearer "+token);

                try{
                    HttpResponse response = httpClient.execute(httpGet);
                    String responseStr = EntityUtils.toString(response.getEntity());
                    // Writing response to log
                    Log.d("HTTP Get Response :", responseStr);
                    blists =(TextView)findViewById(R.id.dataview);
                    blists.setText(responseStr);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            });
    }
}


