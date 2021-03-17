package com.example.bookrental;

public class CartItemModel {

    public static final int Cart_Item = 0;
    public static final int Total_Amount = 0;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /////cartitem

    private int bookImage;
    private String book_Tital;
    private String book_price;

    public CartItemModel(int type, int bookImage, String book_Tital, String book_price) {
        this.type = type;
        this.bookImage = bookImage;
        this.book_Tital = book_Tital;
        this.book_price = book_price;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
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

    ////cartitem

}
