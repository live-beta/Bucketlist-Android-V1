package com.example.sam.bucketlist.views.users;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.sam.bucketlist.R;
import com.example.sam.bucketlist.databinding.LoginActivityBinding;
import com.example.sam.bucketlist.models.LoginFields;
import com.example.sam.bucketlist.models.UserLoginFields;
import com.example.sam.bucketlist.viewmodel.LoginViewModel;
/* Login Activity
* */

public class LoginActivity extends Activity {

    private Context context = this;

    private UserLoginFields userLoginFields = new UserLoginFields();
    private LoginActivityBinding loginActivityBinding;
    private LoginFields loginFields;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        loginActivityBinding = DataBindingUtil.setContentView(this,
                R.layout.login_activity);

    }

    public void login(View view) {

        this.userLoginFields.setUserName(loginActivityBinding.uname.getText().toString());
        this.userLoginFields.setPassword(loginActivityBinding.upass.getText().toString());
        this.loginFields = new LoginFields(this.userLoginFields.getUserName(),
                this.userLoginFields.getPassword());

        loginViewModel = new LoginViewModel(this);

        loginActivityBinding.setLoginViewModel(loginViewModel);

        loginViewModel.userLogin(this.loginFields);

    }

}


