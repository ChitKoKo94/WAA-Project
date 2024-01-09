package com.shopping.Planet.Service;

import com.shopping.Planet.DTO.ProductDTO;
import com.shopping.Planet.Domain.Product;
import org.springframework.beans.BeanUtils;

public class ProductAdapter {
    public static Product getProduct(ProductDTO productDTO) {
        if (productDTO == null)
            return null;

        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }

    public static ProductDTO getProductDTO(Product product) {
        if (product == null)
            return null;

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
