package com.rioc.ws.models.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table (name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id", unique = true, nullable = false)
    private int addressId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "street")
    private String street;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city")
    private String city;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zip_code")
    private String zipCode;




    public Address(int addressId, String street, String city, String zipCode) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public Address(int addressId, String street, String city) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
    }

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
