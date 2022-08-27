package com.example.findtherightimage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findtherightimage.MainActivity
import com.example.findtherightimage.ProductDetails
import com.example.findtherightimage.model.ProductList
import com.example.findtherightimage.R
import com.example.findtherightimage.callBack.ClickItemListProduct

class ProductAdapter(
    private val context: MainActivity,
    private val list: MutableList<ProductList>,
    private val callback: (productList: ProductList,Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolderProDuc>() {


    inner class ViewHolderProDuc(view: View) : RecyclerView.ViewHolder(view) {
        val imaProduct: ImageView = view.findViewById(R.id.imageAnhProduct)

        fun bindItem(productList: ProductList, position: Int) {
            itemView.setOnClickListener {
//                clickProducts.clickItemListProduct(position)

                callback.invoke(productList,position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewHolderProDuc {
        return ViewHolderProDuc(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recy_view_danh_sach_sp, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolderProDuc, position: Int) {
        var productList: ProductList = list.get(position)
        Glide.with(context).load(productList.imageAnhProduct).into(holder.imaProduct)

        holder.bindItem(productList, position)

    }

    override fun getItemCount(): Int {
        return list.size
    }


}