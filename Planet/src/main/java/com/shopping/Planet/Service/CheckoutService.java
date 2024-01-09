package com.shopping.Planet.Service;

import com.shopping.Planet.Domain.CheckoutRecord;
import com.shopping.Planet.Domain.OrderItem;
import com.shopping.Planet.Domain.OrderStatus;
import com.shopping.Planet.Domain.Product;
import com.shopping.Planet.Repo.CheckoutRecordRepo;
import com.shopping.Planet.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRecordRepo checkoutRecordRepo;

    @Autowired
    private ProductRepo productRepo;

    public CheckoutRecord createCheckoutRecord(CheckoutRecord checkoutRecord) {
        List<OrderItem> itemList = checkoutRecord.getOrder().getItems();
        List<Product> productList = itemList.stream()
                .map(i -> i.getProduct())
                .collect(Collectors.toList());
        productList.forEach(product -> {
            productRepo.save(product);
        });
        return checkoutRecordRepo.save(checkoutRecord);
    }

    public CheckoutRecord updateOrderStatus(String id, OrderStatus status) {
        CheckoutRecord record = checkoutRecordRepo.findById(id).get();
        System.out.println("updating checkout obj ID: " + record.getId());
        System.out.println("status " + status);
        record.getOrder().setStatus(status);
        return checkoutRecordRepo.save(record);
    }

    public List<CheckoutRecord> findAll() {
        return checkoutRecordRepo.findAll();
    }
}
