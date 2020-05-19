package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;

import java.math.BigDecimal;

public class UI_WalletController {
    UI_Http_Service service;

    UserInfo userInfo;

    public UI_WalletController(){
        this.service = new UI_Http_Service();
        this.userInfo = new UserInfo();
        userInfo.loadCredentials();
    }

    public boolean createTransaction(Transaction transaction){

        if(transaction == service.postWalletTransaction(transaction, userInfo.getWalletId())){
            return true;
        }else{
            return false;
        }
    }
    public BigDecimal getWalletBalance(){

        return service.getWallet(userInfo.getWalletId()).getBalance();
    }
    public Statistics getExpenseWalletStatistics(){
        return service.getExpenseStatisticsForWallet();
    }
    public Statistics getIncomeWalletStatistics(){
        return service.getIncomeStatisticsForWallet();
    }
    public String getFirstName(){
        return service.getUser(userInfo.getUserId()).getFirstName();
    }
    public String getLastName(){
        return service.getUser(userInfo.getUserId()).getLastName();
    }
}
