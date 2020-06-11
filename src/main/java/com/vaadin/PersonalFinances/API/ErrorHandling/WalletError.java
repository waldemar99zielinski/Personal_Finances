package com.vaadin.PersonalFinances.API.ErrorHandling;

import com.vaadin.PersonalFinances.API.models.Wallet;

import java.math.BigDecimal;

public class WalletError  {
    public WalletError(){

    }
    public Wallet getErrorBody(){
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCurrency("USD");
        return wallet;
    }
}
