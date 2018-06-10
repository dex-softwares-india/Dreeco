package com.example.rohangoyal2014.dreeco.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtil {

    public static void loadImageWithGlide(Context context,String resource,ImageView target)
    {
        Glide.with(context).load(resource).into(target);
    }

}
