package com.shopping.Planet.Controller;

import com.shopping.Planet.DTO.ProductDTO;
import com.shopping.Planet.Domain.Review;
import com.shopping.Planet.Service.ProductService;
import com.shopping.Planet.Validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        String errors = ProductValidator.validateProduct(productDTO);
        if (!errors.isBlank()) {
            throw new IllegalArgumentException(errors);
        }

        ProductDTO productDTOcreated = productService.createProduct(productDTO);
        if (productDTOcreated == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDTOcreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productNumber}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productNumber) {
        boolean isDeleted = productService.deleteProduct(productNumber);
        if (isDeleted) {
            return new ResponseEntity<>("Product: " + productNumber + " is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Deletion failed. Product Number: " + productNumber, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) String name, @RequestParam(required = false) BigDecimal priceGreaterThanOrEqual,
                                    @RequestParam(required = false) String number) {
        System.out.println(productService.search(name, priceGreaterThanOrEqual, number));
        return new ResponseEntity<>(productService.search(name, priceGreaterThanOrEqual, number), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductDTO productDTO) {
        ProductDTO updatedDTO = productService.update(productDTO);
        if (updatedDTO != null) {
            return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/reviews/{productNumber}")
    public ResponseEntity<?> addReview(@PathVariable String productNumber, @RequestBody Review review) {
        ProductDTO productDTOUpdated = productService.addReview(productNumber, review);
        if (productDTOUpdated != null) {
            return new ResponseEntity<>(productDTOUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception exception) {
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("isSuccess", false);
        errorInfo.put("error", exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
