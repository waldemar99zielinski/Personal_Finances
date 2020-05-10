package com.vaadin.PersonalFinances.API.models;

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

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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


    }
}
