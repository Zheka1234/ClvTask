package ru.clevertec.check.model;


public class Product {
    private int id;

    private String name;

    private double price;

    private boolean isWholesale;

    private int quantityInStock;

    public Product(int id, String name, double price, boolean isWholesale, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isWholesale = isWholesale;
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isWholesale() {
        return isWholesale;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}