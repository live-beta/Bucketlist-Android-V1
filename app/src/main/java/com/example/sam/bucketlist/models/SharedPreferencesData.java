package com.example.sam.bucketlist.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sam on 1/5/18.
 */

public class SharedPreferencesData {

    private SharedPreferences sharedPreferences;

    private String token;

    public SharedPreferencesData(String token, Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        this.token = token;

    }

    public SharedPreferencesData() {

    }

    public void setToken() {

        this.sharedPreferences.edit().putString("token", token).apply();

    }

    public String getToken() {

        token = this.sharedPreferences.getString("token", "");
        return token;

    }


}
