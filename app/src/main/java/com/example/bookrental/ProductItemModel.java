package com.example.bookrental;

public class ProductItemModel {

    private String viewAllBookImage;
    private String viewAllBookTitle;
    private String viewAllBookOriginalPrice;
    private String viewAllBookRentalPrice;
    private String viewAllBookRentalTime;

    public ProductItemModel(String viewAllBookImage, String viewAllBookTitle, String viewAllBookOriginalPrice, String viewAllBookRentalPrice, String viewAllBookRentalTime) {
        this.viewAllBookImage = viewAllBookImage;
        this.viewAllBookTitle = viewAllBookTitle;
        this.viewAllBookOriginalPrice = viewAllBookOriginalPrice;
        this.viewAllBookRentalPrice = viewAllBookRentalPrice;
        this.viewAllBookRentalTime = viewAllBookRentalTime;
    }

    public String getViewAllBookImage() {
        return viewAllBookImage;
    }

    public void setViewAllBookImage(String viewAllBookImage) {
        this.viewAllBookImage = viewAllBookImage;
    }

    public String getViewAllBookTitle() {
        return viewAllBookTitle;
    }

    public void setViewAllBookTitle(String viewAllBookTitle) {
        this.viewAllBookTitle = viewAllBookTitle;
    }

    public String getViewAllBookOriginalPrice() {
        return viewAllBookOriginalPrice;
    }

    public void setViewAllBookOriginalPrice(String viewAllBookOriginalPrice) {
        this.viewAllBookOriginalPrice = viewAllBookOriginalPrice;
    }

    public String getViewAllBookRentalPrice() {
        return viewAllBookRentalPrice;
    }

    public void setViewAllBookRentalPrice(String viewAllBookRentalPrice) {
        this.viewAllBookRentalPrice = viewAllBookRentalPrice;
    }

    public String getViewAllBookRentalTime() {
        return viewAllBookRentalTime;
    }

    public void setViewAllBookRentalTime(String viewAllBookRentalTime) {
        this.viewAllBookRentalTime = viewAllBookRentalTime;
    }
}
