package com.shopping.Planet.Domain;

import java.time.LocalDate;

public class Payment {
    private String type;
    private String number;
    private LocalDate date;
    private int validationCode;

    public Payment() {}

    public Payment(String type, String number, LocalDate date, int validationCode) {
        this.type = type;
        this.number = number;
        this.date = date;
        this.validationCode = validationCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(int validationCode) {
        this.validationCode = validationCode;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", validationCode=" + validationCode +
                '}';
    }
}
