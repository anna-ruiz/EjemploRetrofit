package com.example.ejerlistacompracorregido.modelos;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int quantiy;
    private float price;
    private float total;

    public Product(String name, int quantiy, float price) {
        this.name = name;
        this.quantiy = quantiy;
        this.price = price;
        updateTotal();
    }

    private void updateTotal() {
        this.total = this.quantiy * this.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
        updateTotal();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        updateTotal();
    }

    public float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantiy=" + quantiy +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
