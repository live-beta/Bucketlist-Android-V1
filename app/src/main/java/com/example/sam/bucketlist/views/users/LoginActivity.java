package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.UserFields;
import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.views.bucketlists.BucketlistActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends Activity {

    EditText userName,password;
    Button login,forgot_pass;

    ProgressBar progressBar;
    private Handler handler = new Handler();

    private int progressStatus = 0;


    @Override
    protected  void  onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);


        userName = findViewById(R.id.uname);
        password = findViewById(R.id.upass);
        login = findViewById(R.id.login);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                APIManager bucketList = new APIManager(userName.getText().toString(),
                        password.getText().toString());



                 bucketList.login(new Callback<UserFields>() {

                     @Override
                     public void onResponse(Call<UserFields> call, Response<UserFields> response) {

                         ProgressBar(progressBar);

                         try {

                             if (response.body().getToken().isEmpty()) {

                                 Toast.makeText(LoginActivity.this, "Oops! Wring Credentials", Toast.LENGTH_SHORT).show();

                             } else {
                                 Toast.makeText(LoginActivity.this, "Super Dive " + response.body().getToken(), Toast.LENGTH_SHORT).show();

                                 SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                 sp.edit().putString("token", response.body().getToken()).apply();


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

    }
    public void ProgressBar(View view){

            while (progressStatus<100){
                progressStatus += 1;
                progressBar.setProgress(progressStatus);
                progressBar.setVisibility(View.VISIBLE);
            }
        }




}
