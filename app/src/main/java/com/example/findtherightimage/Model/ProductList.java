package com.example.findtherightimage.Model;

import java.util.List;

public class ProductList {
    private int idProduct ;
    private int imageAnhProduct;

    public ProductList() {
    }

    public ProductList(int idProduct, int imageAnhProduct) {
        this.idProduct = idProduct;
        this.imageAnhProduct = imageAnhProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getImageAnhProduct() {
        return imageAnhProduct;
    }

    public void setImageAnhProduct(int imageAnhProduct) {
        this.imageAnhProduct = imageAnhProduct;
    }
}
