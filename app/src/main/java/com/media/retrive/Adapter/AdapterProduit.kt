package com.media.retrive.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media.retrive.R

class AdapterProduit {
    //viewHolder class for product .xml
    inner  class HolderPrduct(itemview: View):RecyclerView.ViewHolder(itemview){

        val iconIv:ImageView = itemview.findViewById(R.id.iconTv)
        val titleTv:TextView = itemview.findViewById(R.id.txtTitletv)
        val ratingbar:RatingBar = itemview.findViewById(R.id.etoile)
        val description:TextView = itemview.findViewById(R.id.descripttion)


    }
}