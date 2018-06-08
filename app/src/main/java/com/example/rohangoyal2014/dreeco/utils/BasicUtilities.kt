package com.example.rohangoyal2014.dreeco.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics



/**
 * Created by nimish on 8/6/18.
 */
object BasicUtilities
{
    fun getScreenSize(context: Context): List<Int>
    {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        return listOf<Int>(height, width)
    }
}