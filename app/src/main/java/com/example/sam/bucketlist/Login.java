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
    EditText userName,password;
    Button login,forgot_pass;
    final Context context = this;
    private BucketListAPICalls bucketListAPICalls = new BucketListAPICalls();

    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        userName = (EditText)findViewById(R.id.uname);
        password =(EditText)findViewById(R.id.upass);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = bucketListAPICalls.login(userName.getText().toString(),password.getText().toString());

                if (status = true){

                    Intent intent = new Intent(context, BucketlistHome.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        forgot_pass =(Button) findViewById(R.id.forgot_password);

        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"I will sort you out with love", Toast.LENGTH_SHORT).show();


            }
        });
    }



}
