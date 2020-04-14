package com.vaadin.PersonalFinances.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "Wallets")
public class Wallet {
    @Id
    private String id;

    private BigDecimal balance;

    public Wallet() {
        this.balance = BigDecimal.ZERO;
    }
    public String getId(){
        return this.id;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }

}
