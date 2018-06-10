package com.example.rohangoyal2014.dreeco.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rohangoyal2014.dreeco.controllers.HomeSliderFragment;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;

import java.util.ArrayList;

public class HomeSliderAdapter extends FragmentPagerAdapter {

    private ArrayList<String> downloadArrayList;

    public HomeSliderAdapter(FragmentManager fm, ArrayList<String> downloadArrayList) {
        super(fm);
        this.downloadArrayList = downloadArrayList;
    }

    @Override
    public Fragment getItem(int position) {
//        Log.e("HomeSliderAdapter",String.valueOf(position));
        HomeSliderFragment homeSliderFragment = new HomeSliderFragment();
        homeSliderFragment.setImageResource(downloadArrayList.get(position));
        return homeSliderFragment;
    }

    @Override
    public int getCount() {
        return ServerUtils.offerDownloadUrls.size();
    }
}
