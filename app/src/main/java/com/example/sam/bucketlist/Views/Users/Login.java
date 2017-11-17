package com.example.sam.bucketlist.Views.Users;

import android.app.Activity;
import android.os.StrictMode;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.sam.bucketlist.BucketListMethods.BucketList;
import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.Views.BucketListItems.BucketlistHome;

/**
 * Created by Sam on 19/03/2017.
 */

public class Login extends Activity {

    EditText userName,password;
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

        userName = (EditText)findViewById(R.id.uname);
        password =(EditText)findViewById(R.id.upass);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BucketList bucketList = new BucketList(userName.getText().toString(),
                        password.getText().toString());


                boolean status = bucketList.login();

                if (status){

                    Intent intent = new Intent(context, BucketlistHome.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgot_pass =(Button) findViewById(R.id.forgot_password);

        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
    }



}
