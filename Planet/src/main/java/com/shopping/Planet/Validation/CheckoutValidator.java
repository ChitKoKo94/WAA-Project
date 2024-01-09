package com.shopping.Planet.Validation;

import com.shopping.Planet.Domain.CheckoutRecord;
import com.shopping.Planet.Domain.Order;
import com.shopping.Planet.Domain.Payment;
import com.shopping.Planet.Domain.User;

public class CheckoutValidator {
    public static String validateCheckout(CheckoutRecord checkoutRecord) {
        return validateOrder(checkoutRecord.getOrder()) +
                validateUser(checkoutRecord.getUser()) +
                validatePayment(checkoutRecord.getPayment());
    }

    private static String validateOrder(Order order) {
        if (order.getItems() == null || order.getItems().size() == 0) {
            return "Mininum 1 item is required.";
        }
        return "";
    }

    private static String validateUser(User user) {
        if (isEmpty(user.getUsername())
            || isEmpty(user.getEmail())
            || isEmpty(user.getPhone())
            || isEmpty(user.getCity())
            || isEmpty(user.getZip())
            || isEmpty(user.getStreet())) {
            return "All fields are required for User";
        }
        return "";
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isBlank();
    }

    private static String validatePayment(Payment payment) {
        if (isEmpty(payment.getType())
        || isEmpty(payment.getNumber())
        || payment.getDate() == null
        || payment.getValidationCode() == 0) {
            return "All fields are required for Payment";
        }
        return "";
    }
}
