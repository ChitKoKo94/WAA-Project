package com.shopping.Planet.Controller;

import com.shopping.Planet.Domain.CheckoutRecord;
import com.shopping.Planet.Domain.OrderStatus;
import com.shopping.Planet.Service.CheckoutService;
import com.shopping.Planet.Validation.CheckoutValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<?> findAllCheckouts() {
        return new ResponseEntity<>(checkoutService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CheckoutRecord checkoutRecord) {
        System.out.println("saving chk out");
        String errorMessage = CheckoutValidator.validateCheckout(checkoutRecord);
        if (!errorMessage.isBlank()) {
            System.out.println("err " + errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        CheckoutRecord record = checkoutService.createCheckoutRecord(checkoutRecord);
        if (record == null) {
            System.out.println("saving failed");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @PutMapping("/{checkoutId}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable String checkoutId, @PathVariable OrderStatus status) {
        CheckoutRecord record = checkoutService.updateOrderStatus(checkoutId, status);
        if (record == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception exception) {
        System.out.println("handling error");
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("isSuccess", false);
        errorInfo.put("error", exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
