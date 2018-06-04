package com.example.rohangoyal2014.dreeco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(getString(R.string.register));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
