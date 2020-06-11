package com.vaadin.PersonalFinances.API.models;

import com.vaadin.PersonalFinances.API.models.currencyModels.Currencies;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Document(collection = "Wallets")
public class Wallet {
    @Id
    private String id;

    private BigDecimal balance;

    private String currency;



    public Wallet() {
        this.balance = BigDecimal.ZERO;
        this.currency = Currencies.USD.toString();
        //this.balance.setScale(2);
    }

    public String getId(){
        return this.id;
    }

    public BigDecimal getBalance(){
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
        this.balance = this.balance.setScale(2, RoundingMode.DOWN);
        //System.out.println("Wallet: setBalance : balance: "+ this.balance);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void changeCurrency(String currency, BigDecimal currencyExchangeValue){
        //System.out.println("Wallet: changeCurrency: " + currency + currencyExchangeValue);
        setCurrency(currency);
        setBalance(balance.multiply(currencyExchangeValue));
    }

    public void setBalanceAddingTransaction(Transaction transaction) {
        //System.out.println("model Wallet: setBalanceAddingTransaction\ntransactionType: "+transaction.getType()+"\nenum output: "+TransactionType.EXPENSE.toString());
        if(TransactionType.EXPENSE.toString().equals(transaction.getType())){
            //System.out.println("model Wallet: setBalanceAddingTransaction :: expense");
            this.balance = this.balance.subtract(transaction.getAmountOfMoney());
        }else if(TransactionType.INCOME.toString().equals(transaction.getType())){
            //System.out.println("model Wallet: setBalanceAddingTransaction :: income");
            this.balance = this.balance.add(transaction.getAmountOfMoney());
        }
        this.balance = this.balance.setScale(2, RoundingMode.DOWN);

    }
}
