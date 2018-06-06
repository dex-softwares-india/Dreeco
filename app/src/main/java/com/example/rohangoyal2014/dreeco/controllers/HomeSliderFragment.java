package com.example.rohangoyal2014.dreeco.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rohangoyal2014.dreeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSliderFragment extends Fragment {


    private ImageView imageView;

    public HomeSliderFragment() {
        // Required empty public constructor
    }


    public void setImageResource(String resource){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_slider, container, false);

        imageView=view.findViewById(R.id.slider_image);

        return view;
    }

}
