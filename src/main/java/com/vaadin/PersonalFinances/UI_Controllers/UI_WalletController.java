package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.Transaction;

public class UI_WalletController {
    UI_Http_Service service;

    public UI_WalletController(){
        this.service = new UI_Http_Service();
    }

    public boolean createTransaction(Transaction transaction, String walletId){
        if(transaction == service.postWalletTransaction(transaction, walletId)){
            return true;
        }else{
            return false;
        }
    }

}
