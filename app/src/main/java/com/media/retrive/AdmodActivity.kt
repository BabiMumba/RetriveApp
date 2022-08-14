package com.media.retrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.media.retrive.Adapter.AdapterProduit
import com.media.retrive.Model.ModelProduct
import kotlinx.android.synthetic.main.activity_admod.*

class AdmodActivity : AppCompatActivity() {

    companion object{
        private const val  TAG = "NATIVE_AD"
    }
    private  lateinit var productRs:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admod)

        MobileAds.initialize(this){inistatus ->
            Log.d(TAG,"onCreate: oninitialize")

        }
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("TESTE DEVICE HERE","TESTE DEVICE HERE")).build()

        )
        title = "publicite native"

        productRs = findViewById(R.id.produiRvs)

        loadProducts()

    }

    private fun loadProducts() {
        val titles = arrayOf(
            "Android 1.0",
            "Android 2.0",
            "Android 2.1",
            "Android 3.1",
            "Android 4.1",
            "Android 5.1",
            "Android 6.1"


        )
        val description = arrayOf(
            "Android Alpha - novembre 23, 2008",
            "Android Beta - Septembre 23, 2009",
            "Android one - october 20, 2010",
            "Android one - mars 20, 2020",
            "Android one - jun 20, 2021",
            "Android one - july 20, 2015",
            "Android one - january 20, 2012",
        )

        val produArrayList = ArrayList<ModelProduct>()

        for (i in titles.indices){
            val model = ModelProduct(R.drawable.man,titles[i],description[i],3.6f)
            produArrayList.add(model)
        }

        val adapterprodut = AdapterProduit(this,produArrayList)

        produiRvs.adapter = adapterprodut

    }
}