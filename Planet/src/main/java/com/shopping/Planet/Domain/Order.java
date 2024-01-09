package com.shopping.Planet.Domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    private OrderStatus status;

    private BigDecimal total;

    public Order() {}

    public Order(List<OrderItem> items, OrderStatus status, BigDecimal total) {
        this.items = items;
        this.status = status;
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
