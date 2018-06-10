package com.example.rohangoyal2014.dreeco.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.google.firebase.auth.FirebaseAuth;


public class FirstTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);


        ServerUtils.mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ServerUtils.offerDownloadUrls.isEmpty()) {
                    retrieveAndSetUpOffers();
                }
                startNewActivity();
            }
        }, 1500);

    }

    void retrieveAndSetUpOffers() {
        ServerUtils.offerDownloadUrls.add("https://dreeco.herokuapp.com/images/offers_home/offer1.jpeg");
        ServerUtils.offerDownloadUrls.add("https://dreeco.herokuapp.com/images/offers_home/offer2.jpeg");
        ServerUtils.offerDownloadUrls.add("https://dreeco.herokuapp.com/images/offers_home/offer3.jpeg");
    }

    private void startNewActivity() {

        startActivity(new Intent(FirstTimeActivity.this, MallActivity.class));
        finish();
    }

}
