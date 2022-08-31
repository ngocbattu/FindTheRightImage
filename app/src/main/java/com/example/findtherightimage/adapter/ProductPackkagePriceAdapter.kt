package com.example.findtherightimage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.ProductDetails
import com.example.findtherightimage.R

class ProductPackkagePriceAdapter (
    private val context: Context,
    private val list: MutableList<ProductDetails>
        ):
    RecyclerView.Adapter<ProductPackkagePriceAdapter.ViewHolderPrice>() {
    class ViewHolderPrice(view : View): RecyclerView.ViewHolder(view) {
        val txtPrice : TextView = view.findViewById(R.id.txtPriceProductDetailsss)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductPackkagePriceAdapter.ViewHolderPrice {
        return ViewHolderPrice(LayoutInflater.from(parent.context).inflate(R.layout.item_recy_view_product_price,parent,false))
    }

    override fun onBindViewHolder(
        holder: ProductPackkagePriceAdapter.ViewHolderPrice,
        position: Int
    ) {
        val productDetails: ProductDetails = list.get(position)
        holder.txtPrice.text = productDetails.title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}