package com.media.retrive.Adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.formats.MediaView
import com.media.retrive.R

class AdapterProduit {
    //viewHolder class for product .xml
    inner  class HolderPrduct(itemview: View):RecyclerView.ViewHolder(itemview){

        val iconIv:ImageView = itemview.findViewById(R.id.iconTv)
        val titleTv:TextView = itemview.findViewById(R.id.txtTitletv)
        val ratingbar:RatingBar = itemview.findViewById(R.id.etoile)
        val description:TextView = itemview.findViewById(R.id.descripttion)

    }
    inner class HoldenativeAds(itemview:View):RecyclerView.ViewHolder(itemview){

        val ad_appicon:ImageView = itemview.findViewById(R.id.ad_app_icon)
        val ad_headline:TextView = itemview.findViewById(R.id.ad_headline)
        val ad_advertiser:TextView = itemview.findViewById(R.id.ad_advertiser)
        val ad_stars:RatingBar = itemview.findViewById(R.id.ad_stars)
        val ad_body:TextView = itemview.findViewById(R.id.ad_body)
        val media_view:MediaView = itemview.findViewById(R.id.media_view)
        val ad_price:TextView = itemview.findViewById(R.id.ad_price)
        val ad_store:TextView = itemview.findViewById(R.id.ad_store)
        val ad_action:Button = itemview.findViewById(R.id.ad_call_to_Action)


    }


}