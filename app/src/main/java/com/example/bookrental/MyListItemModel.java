package com.example.bookrental;

public class MyListItemModel {

    private String productImage;
    private String productTitle;
    private String productLastDate;

    public MyListItemModel(String productImage, String productTitle, String productLastDate) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productLastDate = productLastDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
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
