package com.shopping.Planet.Service;

import com.shopping.Planet.DTO.ProductDTO;
import com.shopping.Planet.Domain.Product;
import com.shopping.Planet.Domain.Review;
import com.shopping.Planet.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Product productSaved = productRepo.save(ProductAdapter.getProduct(productDTO));
        return ProductAdapter.getProductDTO(productSaved);
    }

    public boolean deleteProduct(String productNumber) {
        if (productNumber == null || productNumber.isBlank()) {
            return false;
        }
        Product productToDelete = productRepo.findByProductNumber(productNumber);
        if (productToDelete == null){
            return false;
        }
        productRepo.delete(productToDelete);
        return true;
    }

    public List<ProductDTO> findAll() {
        return productRepo.findAll()
                .stream()
                .map(product -> ProductAdapter.getProductDTO(product))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> search(String name, BigDecimal priceGreaterThanOrEqual, String number) {
        return productRepo.findByNameOrPriceGreaterThanEqualOrProductNumber(name, priceGreaterThanOrEqual, number)
                .stream()
                .map(product -> ProductAdapter.getProductDTO(product))
                .collect(Collectors.toList());
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = productRepo.findByProductNumber(productDTO.getProductNumber());
        if (product != null) {
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            return ProductAdapter.getProductDTO(productRepo.save(product));
        }
        return null;
    }

    public ProductDTO addReview(String productNumber, Review review) {
        Product product = productRepo.findByProductNumber(productNumber);
        if (product == null) {
            return null;
        }
        List<Review> reviews = product.getReviews();
        if (reviews == null) {
            product.setReviews(Arrays.asList(review));
        }
        else {
            reviews.add(review);
        }
        return ProductAdapter.getProductDTO(productRepo.save(product));
    }

}











