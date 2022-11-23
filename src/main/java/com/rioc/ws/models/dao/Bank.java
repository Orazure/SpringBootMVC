package com.rioc.ws.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;
import com.rioc.ws.models.AttributeEncryptor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "bank")
public class Bank {
        // set utf8_general_ci for the table


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_id", unique = true, nullable = false)
        private int bankId;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_name")
        private String bankName;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_code")
        private String bankCode;

        //encryted iban


        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Convert(converter = AttributeEncryptor.class)
//        @ColumnTransformer(forColumn = "bank_iban",read = "AES_DECRYPT(bank_iban, 'secret') as char(255))",write = "AES_ENCRYPT(?,'secret')")
////        @ColumnTransformer(forColumn = "bank_iban", read = "aes_decrypt(bank_iban, 'mySecret')")
        @Column(name = "bank_iban")
        private String bankIban;

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_Country_code")
        private String bankCountryCode;

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
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        private Account account;



        public Bank() {
        }

        public String getBankCode() {
            return bankCode;
        }

    public Bank(int bankId, String bankName, String bankCode, String bankIban, String bankCountryCode, String bankAddress, String bankPhone, String bankEmail, Account account) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.bankIban = bankIban;
        this.bankCountryCode = bankCountryCode;
        this.bankAddress = bankAddress;
        this.bankPhone = bankPhone;
        this.bankEmail = bankEmail;
        this.account = account;
    }

    public String getBankCountryCode() {
        return bankCountryCode;
    }

    public void setBankCountryCode(String bankCountryCode) {
        this.bankCountryCode = bankCountryCode;
    }

    public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
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
