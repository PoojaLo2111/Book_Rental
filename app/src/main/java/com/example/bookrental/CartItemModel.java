package com.example.bookrental;

public class CartItemModel {

    public static final int Cart_Item = 0;
    public static final int Total_Amount = 0;
    /*private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }*/

    /////cartitem

    private String cartBookImage;
    private String cartBookTital;
    private String cartBookOriginalPrice;
    private String cartBookRentalPrice;
    private String cartBookRentTime;

    public CartItemModel(/*int type, */String cartBookImage, String cartBookTital, String cartBookOriginalPrice, String cartBookRentalPrice, String cartBookRentTime) {
        //this.type = type;
        this.cartBookImage = cartBookImage;
        this.cartBookTital = cartBookTital;
        this.cartBookOriginalPrice = cartBookOriginalPrice;
        this.cartBookRentalPrice = cartBookRentalPrice;
        this.cartBookRentTime = cartBookRentTime;
    }

    public String getCartBookImage() {
        return cartBookImage;
    }

    public void setCartBookImage(String cartBookImage) {
        this.cartBookImage = cartBookImage;
    }

    public String getCartBookTital() {
        return cartBookTital;
    }

    public void setCartBookTital(String cartBookTital) {
        this.cartBookTital = cartBookTital;
    }

    public String getCartBookOriginalPrice() {
        return cartBookOriginalPrice;
    }

    public void setCartBookOriginalPrice(String cartBookOriginalPrice) {
        this.cartBookOriginalPrice = cartBookOriginalPrice;
    }

    public String getCartBookRentalPrice() {
        return cartBookRentalPrice;
    }

    public void setCartBookRentalPrice(String cartBookRentalPrice) {
        this.cartBookRentalPrice = cartBookRentalPrice;
    }

    public String getCartBookRentTime() {
        return cartBookRentTime;
    }

    public void setCartBookRentTime(String cartBookRentTime) {
        this.cartBookRentTime = cartBookRentTime;
    }

    /*public CartItemModel(int type, int bookImage, String book_Tital, String book_price) {
        this.type = type;
        this.cartBookImage = bookImage;
        this.book_Tital = book_Tital;
        this.book_price = book_price;
    }

    public int getBookImage() {
        return cartBookImage;
    }

    public void setBookImage(int bookImage) {
        this.cartBookImage = bookImage;
    }

    public String getBook_Tital() {
        return book_Tital;
    }

    public void setBook_Tital(String book_Tital) {
        this.book_Tital = book_Tital;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    ////cartitem*/

}
