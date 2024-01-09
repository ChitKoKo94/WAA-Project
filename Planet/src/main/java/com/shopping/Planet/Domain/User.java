package com.shopping.Planet.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public class User {
//    @Id
//    private String id;

    private String username;
    private String email;
    private String phone;
    private String city;
    private String street;
    private String zip;

    public User() {}

    public User(String username, String email, String phone, String city, String street, String zip) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "User{" +
//                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}

