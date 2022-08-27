package com.example.findtherightimage.model;

public class ProductList {
    private int idProduct ;
    private int imageAnhProduct;
    private String nameProduct;
    private int  priceProduct;

    public ProductList() {

    }

    public ProductList(int idProduct, int imageAnhProduct, String nameProduct, int priceProduct) {
        this.idProduct = idProduct;
        this.imageAnhProduct = imageAnhProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }
}
