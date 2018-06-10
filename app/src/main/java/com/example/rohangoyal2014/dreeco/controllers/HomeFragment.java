package com.example.rohangoyal2014.dreeco.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rohangoyal2014.dreeco.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
//        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                switch (position) {
//                    case 0:
//                        return new HomeSliderFragment();
//                    case 1:
//                        return new HomeSliderFragment();
//                    default:
//                        return new HomeSliderFragment();
//
//                }
//            }
//
//            @Override
//            public int getCount() {
//                return 3;
//            }
//        });
//        circleIndicator.setViewPager(viewPager);


        return v;
    }
}
