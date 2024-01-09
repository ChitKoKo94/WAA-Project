package com.shopping.Planet.Validation;

import com.shopping.Planet.DTO.ProductDTO;

import java.math.BigDecimal;

public class ProductValidator {
    public static String validateProduct(ProductDTO productDTO) {
        StringBuilder errors = new StringBuilder();

        if (productDTO.getProductNumber() == null
        || productDTO.getProductNumber().isBlank()
        || productDTO.getProductNumber().length() < 5) {
            errors.append("Incorrect Product Number. ");
        }
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            errors.append("Product name is required. ");
        }
        if (productDTO.getPrice() == null || productDTO.getPrice().equals(BigDecimal.ZERO)) {
            errors.append("Product price must not be zero. ");
        }
        if (productDTO.getStock() == 0) {
            errors.append("Product stock must be minimum 1.");
        }

        return errors.toString();
    }
}
