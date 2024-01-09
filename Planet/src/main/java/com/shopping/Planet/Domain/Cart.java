package com.shopping.Planet.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Cart {
    @Id
    private String id;

    private List<Product> products;

    public Cart(String id) {}

    public Cart(List<Product> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", products=" + products +
                '}';
    }
}
