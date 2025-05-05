package com.pluralsight;

public class Product {
    private String id;
    private String productName;
    private double price;

    public Product( String id, String productName, double price){
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }


}
