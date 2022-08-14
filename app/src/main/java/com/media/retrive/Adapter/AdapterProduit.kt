package com.media.retrive.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.media.retrive.Model.ModelProduct
import com.media.retrive.R
import java.lang.annotation.Native

class AdapterProduit(
    val context: Context,
    val produArrayList: ArrayList<ModelProduct>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val TAG = "PRODUCT_TAG"
        private const val VIEW_TYPE_CONTENT = 0
        private const val VIEW_TYPE_AD = 1

    }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view:View
        if (viewType== VIEW_TYPE_AD){
            view = LayoutInflater.from(context).inflate(R.layout.row_item_list,parent,false)
            return  HolderPrduct(view)

        }else{
            view = LayoutInflater.from(context).inflate(R.layout.row_native_ads,parent,false)
            return HoldenativeAds(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position)== VIEW_TYPE_CONTENT){

            val model = produArrayList[position]
            val title = model.title
            val description = model.description
            val rating = model.rating
            //instance of our holdeproduit ti access ui view

            val holderPrduct = holder as HolderPrduct
            holderPrduct.titleTv.text = title
            holderPrduct.description.text = description
            holderPrduct.ratingbar.rating = rating

            holder.itemView.setOnClickListener {
                Toast.makeText(context, "$title", Toast.LENGTH_SHORT).show()
            }


        }else if (getItemViewType(position)== VIEW_TYPE_AD){


            //load ad
            val  adLoader = AdLoader.Builder(context, "")
                .forNativeAd {nativeAd->
                    Log.d(TAG,"onNativeAdLoaded")
                    val holdenativeAds = holder as HoldenativeAds
                    displayNativeAd(holdenativeAds, nativeAd)

                }.withAdListener(object : AdListener() {
                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.d(TAG,"onAdClicked")
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Log.d(TAG,"onAdClosed")
                    }

                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        Log.d(TAG,"onAdFailedToLoad")
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Log.d(TAG,"onAdImpression")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        Log.d(TAG,"onAdLoaded")
                    }

                    override fun onAdOpened() {
                        super.onAdOpened()
                        Log.d(TAG,"onAdOpened")

                    }


                }).withNativeAdOptions(NativeAdOptions.Builder().build()).build()
            adLoader.loadAd(AdRequest.Builder().build())


        }
    }

    private fun displayNativeAd(holdenativeAds: AdapterProduit.HoldenativeAds, nativeAd: NativeAd) {
        val headLine = nativeAd.headline
        val body = nativeAd.body
        val callToAction = nativeAd.callToAction
        val icon = nativeAd.icon
        val price = nativeAd.price
        val store = nativeAd.store
        val starating = nativeAd.starRating
        val advertiser = nativeAd.advertiser
        val mediaContent = nativeAd.mediaContent

        //headline
        if (headLine == null){
            holdenativeAds.ad_headline.visibility = View.INVISIBLE
        }else{
            holdenativeAds.ad_headline.visibility = View.VISIBLE
            holdenativeAds.ad_headline.text = headLine
        }
        //body
        if (body == null){
            holdenativeAds.ad_body.visibility = View.INVISIBLE
        }else{
            holdenativeAds.ad_body.visibility = View.VISIBLE
            holdenativeAds.ad_body.text = headLine
        }
        //icone
        if (icon == null){
            holdenativeAds.ad_appicon.visibility = View.INVISIBLE
        }else{
            holdenativeAds.ad_appicon.visibility = View.VISIBLE
            holdenativeAds.ad_appicon.setImageDrawable(icon.drawable)
        }
        //


    }

    override fun getItemCount(): Int {
        return  produArrayList.size

    }


}