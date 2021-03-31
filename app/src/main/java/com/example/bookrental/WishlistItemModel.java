package com.example.bookrental;

public class WishlistItemModel {

    private int wishlistBookImage;
    private String wishlistBookTital;
    private String wishlistBookOriginalPrice;
    private String wishlistBookRentalPrice;
    private String wishlistBookRentTime;

    public WishlistItemModel(int wishlistBookImage, String wishlistBookTital, String wishlistBookOriginalPrice, String wishlistBookRentalPrice, String wishlistBookRentTime) {
        this.wishlistBookImage = wishlistBookImage;
        this.wishlistBookTital = wishlistBookTital;
        this.wishlistBookOriginalPrice = wishlistBookOriginalPrice;
        this.wishlistBookRentalPrice = wishlistBookRentalPrice;
        this.wishlistBookRentTime = wishlistBookRentTime;
    }

    public int getWishlistBookImage() {
        return wishlistBookImage;
    }

    public void setWishlistBookImage(int wishlistBookImage) {
        this.wishlistBookImage = wishlistBookImage;
    }

    public String getWishlistBookTital() {
        return wishlistBookTital;
    }

    public void setWishlistBookTital(String wishlistBookTital) {
        this.wishlistBookTital = wishlistBookTital;
    }

    public String getWishlistBookOriginalPrice() {
        return wishlistBookOriginalPrice;
    }

    public void setWishlistBookOriginalPrice(String wishlistBookOriginalPrice) {
        this.wishlistBookOriginalPrice = wishlistBookOriginalPrice;
    }

    public String getWishlistBookRentalPrice() {
        return wishlistBookRentalPrice;
    }

    public void setWishlistBookRentalPrice(String wishlistBookRentalPrice) {
        this.wishlistBookRentalPrice = wishlistBookRentalPrice;
    }

    public String getWishlistBookRentTime() {
        return wishlistBookRentTime;
    }

    public void setWishlistBookRentTime(String wishlistBookRentTime) {
        this.wishlistBookRentTime = wishlistBookRentTime;
    }
}
