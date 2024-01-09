package com.shopping.Planet.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckoutRecord {

    @Id
    private String id;

    private Order order;
    private User user;
    private Payment payment;

    public CheckoutRecord() {}

    public CheckoutRecord(Order order, User user, Payment payment) {
        this.order = order;
        this.user = user;
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", user=" + user +
                ", payment=" + payment +
                '}';
    }
}
