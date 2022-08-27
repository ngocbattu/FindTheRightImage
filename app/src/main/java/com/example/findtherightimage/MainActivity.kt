package com.example.findtherightimage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.android.billingclient.api.*
import com.example.findtherightimage.Adapter.ViewPageSlidePhoto
import com.example.findtherightimage.Model.SlideShowPhoto
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager
     var mTimer: Timer = Timer()
    lateinit var toolbar: Toolbar
    lateinit var image_cart : ImageView
    lateinit var viewPageSlidePhoto: ViewPageSlidePhoto
    var listslideShowPhoto: MutableList<SlideShowPhoto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)
        viewPageSlidePhoto = ViewPageSlidePhoto(this,listslideShowPhoto)
        image_cart = findViewById(R.id.imageCart)
        viewPager = findViewById(R.id.viewPageProduct)
        toolbar = findViewById(R.id.toolbarHome)
        listslideShowPhoto.add(SlideShowPhoto(R.drawable.slide1))
        listslideShowPhoto.add(SlideShowPhoto(R.drawable.slide2))
        listslideShowPhoto.add(SlideShowPhoto(R.drawable.slide3))
        toolbar.setTitle("Trang chủ")
        viewPager.adapter = ViewPageSlidePhoto(this,listslideShowPhoto)
        AotuSlideImage()

        val skulist  = ArrayList<String>()
        skulist.add("android.test.purchased")

         val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }

         val billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        image_cart.setOnClickListener(View.OnClickListener {

            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    val params = SkuDetailsParams.newBuilder()
                        params.setSkusList(skulist).setType(BillingClient.SkuType.INAPP)

                        billingClient.querySkuDetailsAsync(params.build()){
                            billingResult ,
                                skuDetailsList ->
                            for (skuDetails in skuDetailsList!!){
                                val flow = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build()

                                val responseCode = billingClient.launchBillingFlow(this@MainActivity,flow).responseCode
                            }
                        }
                    }
                }
                override fun onBillingServiceDisconnected() {
                    // Try to restart the connection on the next request to
                    // Google Play by calling the startConnection() method.
                }
            })
            Log.d("TAG", "onCreate:  aaaa" )
        })




    }
    fun AotuSlideImage() {
        if(listslideShowPhoto == null || listslideShowPhoto.isEmpty() || viewPager == null){
            return
        }
        if(mTimer == null){
            mTimer = Timer()
        }

        mTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    var index_anh = viewPager.currentItem
                    val int_list_image = listslideShowPhoto.size - 1
                    if (index_anh < int_list_image) {
                        index_anh++
                        viewPager.currentItem = index_anh
                    } else {
                        viewPager.currentItem = 0
                    }
                }
            }
        }, 6000, 6000)
    }



}









