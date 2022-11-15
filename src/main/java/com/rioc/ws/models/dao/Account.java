package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {


    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id", referencedColumnName = "adress_id")
    private Address address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", unique = true, nullable = false)
    private int accountId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "first_name")
    private String firstName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "last_name")
    private String lastName;


    public Account(int accountId, String firstName, String lastName, Address address) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Account() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}