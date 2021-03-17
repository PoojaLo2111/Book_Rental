package com.example.bookrental;

public class MyListItemModel {

    private int productImage;
    private String productTitle;
    private String productLastDate;

    public MyListItemModel(int productImage, String productTitle, String productLastDate) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productLastDate = productLastDate;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductLastDate() {
        return productLastDate;
    }

    public void setProductLastDate(String productLastDate) {
        this.productLastDate = productLastDate;
    }
}
