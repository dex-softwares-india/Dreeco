package com.example.rohangoyal2014.dreeco.controllers;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.adapters.MallFragmentPagerAdapter;

public class MallActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        viewPager=findViewById(R.id.viewpager);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        MallFragmentPagerAdapter adapter=new MallFragmentPagerAdapter(getSupportFragmentManager(),bottomNavigationView);
        viewPager.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nearby:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }
}
