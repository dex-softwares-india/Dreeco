package com.example.rohangoyal2014.dreeco.controllers;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.models.LoginModel;
import com.example.rohangoyal2014.dreeco.presenters.LoginPresenter;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.example.rohangoyal2014.dreeco.views.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirstTimeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        ServerUtils.mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FirstTimeActivity.this,MallActivity.class));
                finish();
            }
        },2500);

    }
}
