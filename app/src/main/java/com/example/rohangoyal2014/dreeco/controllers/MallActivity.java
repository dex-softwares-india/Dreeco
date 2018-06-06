package com.example.rohangoyal2014.dreeco.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.adapters.MallFragmentPagerAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.lang.reflect.Field;

public class MallActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    MaterialSearchView searchView;
    Toolbar toolbar;
    public MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        viewPager=findViewById(R.id.viewpager);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        searchView=findViewById(R.id.search_view);
        toolbar=findViewById(R.id.toolbar);

        setUpPagerAdapter();

//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });


        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.home:
                        menuItem.setVisible(true);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nearby:
                        menuItem.setVisible(false);
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        menuItem.setVisible(false);
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public void setUpPagerAdapter(){
        MallFragmentPagerAdapter adapter=new MallFragmentPagerAdapter(getSupportFragmentManager() ,bottomNavigationView);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mall_search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        this.menuItem=item;
        searchView.setMenuItem(item);
        return true;
    }

    void setupSearchView(){

        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }
}
