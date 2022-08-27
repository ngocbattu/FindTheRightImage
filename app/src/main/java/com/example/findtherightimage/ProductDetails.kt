package com.example.findtherightimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.billingclient.api.*
import com.android.billingclient.api.ProductDetails
import com.bumptech.glide.Glide
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList

class ProductDetails : AppCompatActivity(),PurchasesUpdatedListener {
    private val inapp_type_1 = "free_image_animal_15_day"
    private val inapp_type_2 = "free_image_animal_1_day"
    private val inapp_type_3 = "free_image_animal_30_day"
    private val inapp_type_4 = "free_image_animal_3_day"
    private val inapp_type_5 = "free_image_animal_7_day"

    lateinit var imageDetailsProduct : ImageView
    lateinit var txtNameProduct : TextView
    lateinit var txtPriceProduct : TextView
    lateinit var btnMuaHang : Button

    private var listProductDetails = mutableListOf<ProductDetails>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        imageDetailsProduct = findViewById(R.id.imageDetailsProsuct)
        txtNameProduct = findViewById(R.id.txtNameProductDetails)
        txtPriceProduct = findViewById(R.id.txtPriceProductDetails)
        btnMuaHang = findViewById(R.id.btnMuaHang)
        val i = intent
        Glide.with(this).load(i.getIntExtra("_imageProduct",0)).into(imageDetailsProduct)
        txtNameProduct.text = i.getStringExtra("_nameProduct")
        txtPriceProduct.text = i.getIntExtra("_priceProduct",0).toString()
        Log.d("TAG", "onCreate: " + i.getStringExtra("_nameProduct"))

         val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }

         var billingClient = BillingClient.newBuilder(this@ProductDetails)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        btnMuaHang.isEnabled = false
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    val queryProductDetailsParams =
                        QueryProductDetailsParams.newBuilder()
                            .setProductList(
                                ImmutableList.of(
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_1)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                            .setProductId(inapp_type_2)
                                            .setProductType(BillingClient.ProductType.INAPP)
                                            .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                            .setProductId(inapp_type_3)
                                            .setProductType(BillingClient.ProductType.INAPP)
                                            .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                            .setProductId(inapp_type_4)
                                            .setProductType(BillingClient.ProductType.INAPP)
                                            .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                            .setProductId(inapp_type_5)
                                            .setProductType(BillingClient.ProductType.INAPP)
                                            .build(),
                                ))
                            .build()

                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
                            billingResult,
                            productDetailsList ->

                        listProductDetails = productDetailsList

                        btnMuaHang.isEnabled = true

                    }

                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })

        btnMuaHang.setOnClickListener {
            try {
                for (i in listProductDetails.indices){
                    val productDetailsParamsList = listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                            .setProductDetails(
                                listProductDetails.get(i)
                            )
                            // to get an offer token, call ProductDetails.subscriptionOfferDetails()
                            // for a list of offers that are available to the user
                            .build()
                    )
                    Log.d("hunghkp", "onPurchasesUpdated: {${listProductDetails.get(i)}}")
                    val billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build()
                    val billingResult =
                        billingClient.launchBillingFlow(this@ProductDetails, billingFlowParams)
                }


            }catch (e : Exception){
                e.printStackTrace()
            }

        }

    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {

    }


}