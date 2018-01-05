package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.SharedPreferencesData;
import com.example.sam.bucketlist.models.UserFields;
import com.example.sam.bucketlist.views.bucketlists.BucketlistActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends Activity {

    EditText userName,password;
    Button login,register;

    ProgressBar progressBar;

    private int progressStatus = 0;

    private Context context =this;


    @Override
    protected  void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);


        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");

        userName = findViewById(R.id.uname);
        userName.setTypeface(face);
        password = findViewById(R.id.upass);
        password.setTypeface(face);

        login = findViewById(R.id.login);
        login.setTypeface(face);


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        register= findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                APIManager apiManager = new APIManager(userName.getText().toString(),
                        password.getText().toString());



                 apiManager.login(new Callback<UserFields>() {

                     @Override
                     public void onResponse(Call<UserFields> call, Response<UserFields> response) {

                         ProgressBar(progressBar);

                         try {

                             if (response.body().getToken().isEmpty()) {

                                 Toast.makeText(LoginActivity.this, "Oops! Wring Credentials", Toast.LENGTH_SHORT).show();

                             } else {
                                 Toast.makeText(LoginActivity.this, "Super Dive " + response.body().getToken(), Toast.LENGTH_SHORT).show();

                                 SharedPreferencesData sharedPreferencesData = new SharedPreferencesData(response.body().getToken(),context);
                                 sharedPreferencesData.setToken();
                                 Intent intent = new Intent(LoginActivity.this, BucketlistActivity.class);
                                 LoginActivity.this.startActivity(intent);

                             }

                         } catch (Exception e) {

                             Log.d("LoginActivity Error", e.getLocalizedMessage());
                             Toast.makeText(LoginActivity.this, "Oops! Wring Credentials", Toast.LENGTH_SHORT).show();

                         }
                     }

                     @Override
                     public void onFailure(Call<UserFields> call, Throwable t) {
                         Log.d("LoginActivity Error", t.getLocalizedMessage());
                     }
                 });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Register.class);
                startActivity(intent);
            }
        });

    }
    public void ProgressBar(View view){

            while (progressStatus<100){
                progressStatus += 1;
                progressBar.setProgress(progressStatus);
                progressBar.setVisibility(View.VISIBLE);
            }
        }




}
