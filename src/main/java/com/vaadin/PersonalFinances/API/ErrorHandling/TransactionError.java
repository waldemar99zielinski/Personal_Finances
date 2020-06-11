package com.vaadin.PersonalFinances.API.ErrorHandling;

import com.vaadin.PersonalFinances.API.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionError {
    public TransactionError(){

    }

    public Transaction getTransactionErrorBody(){
        Transaction transaction = new Transaction();

        return  transaction;
    }
    public List<Transaction> getTransactionListErrorBody(){
        List<Transaction> transactions = new ArrayList<>();

        return transactions;
    }
}
