package com.example.findtherightimage.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.findtherightimage.Model.ProductList
import com.example.findtherightimage.R

class ProductAdapter(
    private val context: Context,
    private val list : MutableList<ProductList>
): RecyclerView.Adapter<ProductAdapter.ViewHolderProDuc>() {
    class ViewHolderProDuc(view: View):RecyclerView.ViewHolder(view){
        val imaProduct : ImageView = view.findViewById(R.id.imageAnhProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ProductAdapter.ViewHolderProDuc {
         return ViewHolderProDuc(LayoutInflater.from(parent.context).inflate(R.layout.item_recy_view_danh_sach_sp,parent,false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolderProDuc, position: Int) {
        var productList : ProductList = list.get(position)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}