package com.example.bookrental;

public class HorizontalBookScrollModel {

    private int bookImage;
    private String bookTitle;
    private String bookPrice;
    private String bookRentTime;

    public HorizontalBookScrollModel(int bookImage, String bookTitle, String bookPrice, String bookRentTime) {
        this.bookImage = bookImage;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookRentTime = bookRentTime;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookRentTime() {
        return bookRentTime;
    }

    public void setBookRentTime(String bookRentTime) {
        this.bookRentTime = bookRentTime;
    }
}
