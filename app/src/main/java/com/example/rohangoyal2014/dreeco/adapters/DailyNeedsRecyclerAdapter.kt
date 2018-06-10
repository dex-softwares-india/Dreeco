package com.example.rohangoyal2014.dreeco.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rohangoyal2014.dreeco.R
import com.example.rohangoyal2014.dreeco.utils.BasicUtilities
import com.example.rohangoyal2014.dreeco.utils.Product

/**
 * Created by nimish on 8/6/18.
 */
class DailyNeedsRecyclerAdapter// data is passed into the constructor
internal constructor(private val mContext: Context, private val mData: List<Product>) : RecyclerView.Adapter<DailyNeedsRecyclerAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        this.mInflater = LayoutInflater.from(mContext)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.daily_need_home, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = mData[position]
        val resources = mContext.resources
        val resourceId = resources.getIdentifier(product.image, "drawable",
                mContext.packageName)
        holder.myImageView.setImageDrawable(resources.getDrawable(resourceId))
        holder.titleTextView.setText(mData[position].title)
        var screenSize = BasicUtilities.getScreenSize(mContext)
        var imageDimension = screenSize[1]
        holder.titleTextView.layoutParams.width = imageDimension / 4
        holder.myImageView.layoutParams.width = imageDimension / 4
        holder.myImageView.layoutParams.height = holder.myImageView.layoutParams.width
        holder.myImageView.scaleType = ImageView.ScaleType.FIT_XY
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }


    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var myImageView: ImageView
        internal var titleTextView: TextView

        init {
            myImageView = itemView.findViewById(R.id.imageDailyListItem)
            titleTextView = itemView.findViewById(R.id.daily_needs_type_title)
        }

    }

    // convenience method for getting data at click position
    internal fun getItem(id: Int): Product {
        return mData[id]
    }

}
