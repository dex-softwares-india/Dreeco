package com.example.rohangoyal2014.dreeco.adapters;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rohangoyal2014.dreeco.controllers.HomeFragment;
import com.example.rohangoyal2014.dreeco.controllers.HomeMainFragment;

public class MallFragmentPagerAdapter extends FragmentPagerAdapter {

    BottomNavigationView bottomNavigationView;

    public MallFragmentPagerAdapter(FragmentManager fm, BottomNavigationView bottomNavigationView) {
        super(fm);
        this.bottomNavigationView = bottomNavigationView;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeMainFragment();
            case 1:
                return new HomeFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new HomeFragment();
            case 4:
                return new HomeFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
