package com.example.sam.bucketlist;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Created by Sam on 19/03/2017.
 */

public class Login extends AppCompatActivity {
    EditText uname,upass;
    Button login,forgot_pass;
    final Context context = this;

    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        uname = (EditText)findViewById(R.id.uname);
        upass =(EditText)findViewById(R.id.upass);
        login = (Button) findViewById(R.id.login);
        /* If someone clicks on login*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*get the user information */
                String user_name = uname.getText().toString();
                String user_password = upass.getText().toString();

                /* Check if the user has supplied all the required information */
                if (user_name == "" || user_password == "") {
                    Toast.makeText(getApplicationContext(),"Enter all the required fields",Toast.LENGTH_SHORT).show();
                }else {
                    /*Make a login post request*/

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/auth/login");


                    /* Creadentials payload*/
                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                    nameValuePair.add(new BasicNameValuePair("username",user_name));
                    nameValuePair.add(new BasicNameValuePair("password",user_password));
                    /*Encoding POST data*/
                    try {
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                    }catch (UnsupportedEncodingException e){
                        //log exception
                        e.printStackTrace();
                    }
                    /* Make post request*/
                    try{
                        HttpResponse response = httpClient.execute(httpPost);
                        String responseStr = EntityUtils.toString(response.getEntity());
                        Log.d("HTTP Post Response :", responseStr);

                        JSONObject resp = new JSONObject(responseStr);
                        Log.d("HTTP JSON :", String.valueOf(resp));

                        Intent intent = new Intent(context,BucketlistHome.class);
                        String token = resp.getString("token");
                        intent.putExtra("Token",token);
                        startActivity(intent);

                    } catch (ClientProtocolException e){

                    } catch (IOException e){
                        // Log exception
                        e.printStackTrace();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        forgot_pass =(Button) findViewById(R.id.forgot_password);
        /*If someone forgets their password*/
        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"I will sort you out with love", Toast.LENGTH_SHORT).show();


            }
        });
    }



}
