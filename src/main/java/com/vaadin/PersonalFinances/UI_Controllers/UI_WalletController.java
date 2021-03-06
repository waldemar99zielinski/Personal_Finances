package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.ErrorHandling.WalletError;
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
    public Wallet getWallet(){
        try{


            return  service.getWallet(userInfo.getWalletId());
        }
        catch (Exception e){
            System.out.println("UI_WalletController: getWallet: Exception: " + e.getMessage());
            return new WalletError().getErrorBody();

        }

    }
    public Wallet changeWalletCurrency(String newCurrency){
        return service.changeWalletCurrency(userInfo.getWalletId(), newCurrency);
    }
}
