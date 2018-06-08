package com.example.rohangoyal2014.dreeco.controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.rohangoyal2014.dreeco.R
import com.example.rohangoyal2014.dreeco.adapters.DailyNeedsRecyclerAdapter
import com.example.rohangoyal2014.dreeco.services.HomeScreenDataService
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


/**
 * A simple [Fragment] subclass.
 */
class HomeMainFragment : Fragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
       var v:View = inflater.inflate(R.layout.fragment_home_screen, container, false)
       var recyclerView:RecyclerView=v.findViewById(R.id.dailyneed)
       var carouselView:CarouselView=v.findViewById(R.id.homeScreencarouselView)
       setupRecyclerView(recyclerView)
       setupCarouselView(carouselView)

        return v
    }

    fun setupRecyclerView(recyclerView: RecyclerView)
    {
        val products = HomeScreenDataService.dailyNeeds
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        var adapter=DailyNeedsRecyclerAdapter(context!!,products)
        recyclerView.setAdapter(adapter)
    }

    fun setupCarouselView(carouselView: CarouselView)
    {
        val sampleImages= mutableListOf<Int>()
        val resources = context!!.resources
        for (image in HomeScreenDataService.carouselImages)
        {
            val resourceId = resources.getIdentifier(image, "drawable", context!!.packageName)
            sampleImages.add(resourceId)
        }

//        val sampleImages = intArrayOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5)
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(object:ImageListener
        {
            override fun setImageForPosition(position: Int, imageView: ImageView)
            {
                imageView.setImageResource(sampleImages[position])
                imageView.scaleType=ImageView.ScaleType.FIT_XY
            }
        })


    }
}