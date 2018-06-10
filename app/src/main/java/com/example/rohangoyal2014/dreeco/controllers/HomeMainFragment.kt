package com.example.rohangoyal2014.dreeco.controllers

import android.media.Image
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.rohangoyal2014.dreeco.R
import com.example.rohangoyal2014.dreeco.adapters.DailyNeedsRecyclerAdapter
import com.example.rohangoyal2014.dreeco.adapters.HomeSliderAdapter
import com.example.rohangoyal2014.dreeco.utils.GlideUtil
import com.example.rohangoyal2014.dreeco.utils.HomeScreenDataService
import com.example.rohangoyal2014.dreeco.utils.ServerUtils
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

/**
 * A simple [Fragment] subclass.
 */
class HomeMainFragment : Fragment() {

    var carouselView: CarouselView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v: View = inflater.inflate(R.layout.fragment_home_screen, container, false)

        carouselView = v.findViewById(R.id.carousel)


        var recyclerView: RecyclerView = v.findViewById(R.id.dailyneed)
        setupRecyclerView(recyclerView)


        if (!ServerUtils.offerDownloadUrls.isEmpty()) {
            Log.e( "HomeMainFragment","View Recreated")
            carouselView?.setPageCount(ServerUtils.offerDownloadUrls.size)
            carouselView?.setImageListener(object:ImageListener {
                override fun setImageForPosition(position: Int, imageView: ImageView) {
                    GlideUtil.loadImageWithGlide(context,ServerUtils.offerDownloadUrls.get(position),imageView)
                }
            })
        }

        return v
    }

    override fun onStop() {
        super.onStop()

    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        val products = HomeScreenDataService.dailyNeeds
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = DailyNeedsRecyclerAdapter(context!!, products)
        recyclerView.setAdapter(adapter)
    }

}