package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;
import com.example.sam.bucketlist.models.datamodels.UserFields;
import com.example.sam.bucketlist.views.bucketlists.BucketlistActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* Login Activity
* */

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText userName, password;
    Button login, register;

    ProgressBar progressBar;
    private int progressStatus = 0;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");


        userName = findViewById(R.id.uname);
        userName.setTypeface(face);
        password = findViewById(R.id.upass);
        password.setTypeface(face);
        login = findViewById(R.id.login);
        login.setTypeface(face);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        register = findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    public void ProgressBar(View view) {

        while (progressStatus < 100) {
            progressStatus += 1;
            progressBar.setProgress(progressStatus);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == login) {

            APIManager apiManager = new APIManager(userName.getText().toString(),
                    password.getText().toString(), getApplicationContext());

            ProgressBar(progressBar);


            Call<UserFields> call = apiManager.login();

            call.enqueue(new Callback<UserFields>() {
                @Override
                public void onResponse(Call<UserFields> call, Response<UserFields> response) {

                    String token = response.body().getToken();

                    if (token == null) {
                        Toast.makeText(getApplicationContext(), "Login is unsuccessful ",
                                Toast.LENGTH_LONG).show();

                        /*Stop progressbar on completion*/

                        if (progressBar.isShown()) {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    } else {

                         /* If token is present, Login successful*/

                        SharedPreferences preferences = PreferenceManager.
                                getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token", response.body().getToken());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this,
                                BucketlistActivity.class);
                        LoginActivity.this.startActivity(intent);

                        /* Stop the progress bar on completion*/

                        if (progressBar.isShown()) {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }

                }

                @Override
                public void onFailure(Call<UserFields> call, Throwable t) {

                }
            });

        } else if (view == register) {
            Intent intent = new Intent(context, Register.class);
            startActivity(intent);
        }
    }


}
