package com.example.rohangoyal2014.dreeco.utils;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rohangoyal2014.dreeco.HomeFragment;
import com.example.rohangoyal2014.dreeco.NearbyFragment;
import com.example.rohangoyal2014.dreeco.ProfileFragment;
import com.example.rohangoyal2014.dreeco.R;

import java.util.ArrayList;

public class MallFragmentPagerAdapter extends FragmentPagerAdapter{

    BottomNavigationView bottomNavigationView;

    public MallFragmentPagerAdapter(FragmentManager fm, BottomNavigationView bottomNavigationView) {
        super(fm);
        this.bottomNavigationView=bottomNavigationView;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                //bottomNavigationView.setSelectedItemId(R.id.home);
                return new HomeFragment();
            case 1:
                //bottomNavigationView.setSelectedItemId(R.id.nearby);
                return new NearbyFragment();
            default:
                //bottomNavigationView.setSelectedItemId(R.id.profile);
                return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
