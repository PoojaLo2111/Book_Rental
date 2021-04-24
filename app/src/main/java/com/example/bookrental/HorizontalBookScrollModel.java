package com.example.bookrental;

public class HorizontalBookScrollModel {

    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public HorizontalBookScrollModel(){

    }

    private String bookImage;
    private String bookTitle;
    private String bookPrice;
    private String bookRentTime;

    public HorizontalBookScrollModel(String bookImage, String bookTitle, String bookPrice, String bookRentTime) {
        this.bookImage = bookImage;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookRentTime = bookRentTime;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
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
