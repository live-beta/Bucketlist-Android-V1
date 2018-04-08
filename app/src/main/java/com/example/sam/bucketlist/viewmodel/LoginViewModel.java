package com.example.sam.bucketlist.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.sam.bucketlist.api.ApiFactory;
import com.example.sam.bucketlist.apiservices.BucketListService;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserLoginFields;
import com.example.sam.bucketlist.views.bucketlists.BucketlistActivity;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 4/9/18.
 */

public class LoginViewModel extends Observable {

    @NonNull
    private Context context;


    public LoginViewModel(Context context) {
        this.context = context;

    }

    public void userLogin(LoginFields loginFields) {
        BucketListService bucketListService = ApiFactory.create(context);

        Call<UserLoginFields> call = bucketListService.login(loginFields);
        call.enqueue(new Callback<UserLoginFields>() {
            @Override
            public void onResponse(@NonNull Call<UserLoginFields> call, @NonNull Response<UserLoginFields> response) {
                String token = String.valueOf(response.body().getToken());

                if (token == null) {
                    Toast.makeText(context, "Login is unsuccessful ",
                            Toast.LENGTH_LONG).show();


                } else {

                    SharedPreferences preferences = PreferenceManager.
                            getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.apply();

                    Intent intent = new Intent(context,
                            BucketlistActivity.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserLoginFields> call, Throwable t) {
                Toast.makeText(context, "Check Connection", Toast.LENGTH_LONG).show();
            }
        });


    }


}
