package com.example.bookrental;

public class ToRentItemModel {

    private int toRentBookImage;
    private String toRentBookTital;
    private String toRentOriginalPrice;
    private String toRentBookRentalPrice;
    private String toRentBookRentTime;

    public ToRentItemModel(int toRentBookImage, String toRentBookTital, String toRentOriginalPrice, String toRentBookRentalPrice, String toRentBookRentTime) {
        this.toRentBookImage = toRentBookImage;
        this.toRentBookTital = toRentBookTital;
        this.toRentOriginalPrice = toRentOriginalPrice;
        this.toRentBookRentalPrice = toRentBookRentalPrice;
        this.toRentBookRentTime = toRentBookRentTime;
    }

    public int getToRentBookImage() {
        return toRentBookImage;
    }

    public void setToRentBookImage(int toRentBookImage) {
        this.toRentBookImage = toRentBookImage;
    }

    public String getToRentBookTital() {
        return toRentBookTital;
    }

    public void setToRentBookTital(String toRentBookTital) {
        this.toRentBookTital = toRentBookTital;
    }

    public String getToRentOriginalPrice() {
        return toRentOriginalPrice;
    }

    public void setToRentOriginalPrice(String toRentOriginalPrice) {
        this.toRentOriginalPrice = toRentOriginalPrice;
    }

    public String getToRentBookRentalPrice() {
        return toRentBookRentalPrice;
    }

    public void setToRentBookRentalPrice(String toRentBookRentalPrice) {
        this.toRentBookRentalPrice = toRentBookRentalPrice;
    }

    public String getToRentBookRentTime() {
        return toRentBookRentTime;
    }

    public void setToRentBookRentTime(String toRentBookRentTime) {
        this.toRentBookRentTime = toRentBookRentTime;
    }
}
