package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.api.APIManager;

/**
 * Class for registration of a new user
 */

public class Register extends Activity {

    EditText username, email, password;
    Button register;
    APIManager apiManager = new APIManager();

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

                apiManager.registerUser(name, userPassword, userEmail);
                Toast.makeText(getApplicationContext(), "You have been registered", Toast.LENGTH_LONG);

            }
        });

    }
}
