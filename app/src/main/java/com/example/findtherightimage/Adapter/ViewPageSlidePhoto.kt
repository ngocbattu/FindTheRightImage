package com.example.findtherightimage.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.findtherightimage.Model.SlideShowPhoto
import com.example.findtherightimage.R

class ViewPageSlidePhoto(
    private val context: Context ,
    private val listSlideShow : MutableList<SlideShowPhoto>
): PagerAdapter() {
    override fun getCount(): Int {
        return listSlideShow.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_vew_page_slide_show_photo, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageSlidePhotoViewPage)
        val photo = listSlideShow[position]
        Glide.with(context).load(photo.imageSlide).into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}