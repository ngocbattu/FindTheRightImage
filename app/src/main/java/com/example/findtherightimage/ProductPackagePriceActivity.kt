package com.example.findtherightimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.ProductDetails
import com.example.findtherightimage.adapter.ProductPackkagePriceAdapter

class ProductPackagePriceActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private var listProductDetails : MutableList<ProductDetails> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_package_price)
        recyclerView = findViewById(R.id.rcyPricePackegePrice)
        recyclerView.layoutManager = GridLayoutManager(this@ProductPackagePriceActivity, 2)
        recyclerView.adapter = ProductPackkagePriceAdapter(this@ProductPackagePriceActivity, listProductDetails)
    }
}