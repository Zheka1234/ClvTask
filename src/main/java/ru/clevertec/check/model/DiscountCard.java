package ru.clevertec.check.model;


public class DiscountCard {

    private String number;

    private double discountPercentage;

    public DiscountCard(String number, double discountPercentage) {
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public String getNumber() {
        return number;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}