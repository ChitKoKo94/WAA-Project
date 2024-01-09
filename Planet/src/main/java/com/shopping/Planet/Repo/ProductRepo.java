package com.shopping.Planet.Repo;

import com.shopping.Planet.Domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
    Product findByProductNumber(String number);
    List<Product> findByNameOrPriceGreaterThanEqualOrProductNumber(String name, BigDecimal price, String number);
}
