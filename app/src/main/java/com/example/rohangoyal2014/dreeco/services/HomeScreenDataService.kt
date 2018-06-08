package com.example.rohangoyal2014.dreeco.services

import com.example.rohangoyal2014.dreeco.models.Product

/**
 * Created by nimish on 8/6/18.
 */
object HomeScreenDataService
{
    var dailyNeeds= listOf<Product>(
            Product("MILK","milk"),
            Product("BREAD","bread"),
            Product("FRUITS","fruits"),
            Product("EGGS","eggs")
    )

    var carouselImages= listOf<String>(
            "image_1",
            "image_2",
            "image_3"
    )
}