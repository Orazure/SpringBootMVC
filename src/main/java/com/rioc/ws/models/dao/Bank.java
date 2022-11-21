package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "bank")
public class Bank {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_id", unique = true, nullable = false)
        private int bankId;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_name")
        private String bankName;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_iban")
        private String bankIban;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_address")
        private String bankAddress;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_phone")
        private String bankPhone;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_email")
        private String bankEmail;

        @ManyToOne
        @JoinColumn(name = "account_id", nullable = false)
        private Account account;



        public Bank() {
        }



        public Bank(int bankId, String bankName, String bankIban, String bankAddress, String bankPhone, String bankEmail, Account account) {
            this.bankId = bankId;
            this.bankName = bankName;
            this.bankIban = bankIban;
            this.bankAddress = bankAddress;
            this.bankPhone = bankPhone;
            this.bankEmail = bankEmail;
            this.account = account;
        }



        public void setAccount(Account account) {
            this.account = account;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

    public String getBankIban() {
        return bankIban;
    }

    public void setBankIban(String bankIban) {
        this.bankIban = bankIban;
    }

    public Account getAccount() {
        return account;
    }

    public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getBankPhone() {
            return bankPhone;
        }

        public void setBankPhone(String bankPhone) {
            this.bankPhone = bankPhone;
        }

        public String getBankEmail() {
            return bankEmail;
        }

        public void setBankEmail(String bankEmail) {
            this.bankEmail = bankEmail;
        }

}
