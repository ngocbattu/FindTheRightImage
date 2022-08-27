package com.example.findtherightimage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.findtherightimage.adapter.ProductAdapter
import com.example.findtherightimage.adapter.ViewPageSlidePhoto
import com.example.findtherightimage.model.ProductList
import com.example.findtherightimage.model.SlideShowPhoto
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager
     var mTimer: Timer = Timer()
    lateinit var toolbar: Toolbar
    lateinit var image_cart : ImageView
    lateinit var viewPageSlidePhoto: ViewPageSlidePhoto
    var listslideShowPhoto: MutableList<SlideShowPhoto> = mutableListOf()
    var listProduct : MutableList<ProductList> = mutableListOf()
    lateinit var rcyListProduct : RecyclerView
    lateinit var productAdapter : ProductAdapter


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

        image_cart.setOnClickListener(View.OnClickListener {

        })

        insertListProduct()
        getListProductRecyView()


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

    fun insertListProduct(){
        listProduct.add(ProductList(0,R.drawable.cat1,"Mèo bông cute",100000))
        listProduct.add(ProductList(1,R.drawable.cat2,"Mèo bông" , 200000))
        listProduct.add(ProductList(2,R.drawable.cat3,"Mèo bông" , 50000))
        listProduct.add(ProductList(3,R.drawable.dog1,"Chó bông" , 200000))
        listProduct.add(ProductList(4,R.drawable.gau6,"gấu bông" , 200000))
        listProduct.add(ProductList(5,R.drawable.gau7,"gấu bông" , 200000))
        listProduct.add(ProductList(6,R.drawable.gau8,"gấu bông" , 200000))
    }

    fun getListProductRecyView(){
        rcyListProduct = findViewById(R.id.rcyListProduct)
        rcyListProduct.layoutManager = GridLayoutManager(this,2)
        productAdapter = ProductAdapter(this,listProduct){productList: ProductList,position ->
            val intent = Intent(this@MainActivity,ProductDetails::class.java)
            intent.putExtra("_id",productList.idProduct)
            intent.putExtra("_imageProduct",productList.imageAnhProduct)
            intent.putExtra("_nameProduct",productList.nameProduct)
            intent.putExtra("_priceProduct",productList.priceProduct)
            startActivity(intent)
        }
        rcyListProduct.adapter = productAdapter
    }




}









