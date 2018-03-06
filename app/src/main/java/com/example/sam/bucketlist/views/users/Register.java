package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.retrofitmodels.UserDetailPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class for registration of a new user
 */

public class Register extends Activity {

    final Context context = this;
    EditText username, email, password;
    Button register;
    APIManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_user);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);


        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                apiManager = new APIManager(context);

                Call<UserDetailPost> call = apiManager.registerUser(name, userEmail, userPassword);

                call.enqueue(new Callback<UserDetailPost>() {
                    @Override
                    public void onResponse(Call<UserDetailPost> call, Response<UserDetailPost> response) {

                        Log.d("Reg response ", String.valueOf(response.message()));
                        Toast.makeText(context, "User Registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserDetailPost> call, Throwable t) {

                        Log.d("Error", t.getMessage());

                    }
                });

                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}
