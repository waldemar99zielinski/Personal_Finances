package com.vaadin.PersonalFinances.API.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "Transactions")
public class Transaction {
    @Id
    private String id;

    private String title;

    private Date date;

    private String category;



    private String type;

    private BigDecimal amountOfMoney;

    private String walletId;
    public Transaction(){

    }
    public Transaction( String title, String category,Date date, String type, BigDecimal amountOfMoney, String walletId) {

        this.title = title;
        this.category = category;
        this.type = type;
        this.amountOfMoney = amountOfMoney;
        this.walletId = walletId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
