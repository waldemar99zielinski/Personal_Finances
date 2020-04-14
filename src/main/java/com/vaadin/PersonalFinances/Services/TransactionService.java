package com.vaadin.PersonalFinances.Services;

import com.vaadin.PersonalFinances.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.Repositories.UserRepository;
import com.vaadin.PersonalFinances.models.Transaction;
import com.vaadin.PersonalFinances.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {

        return transactionRepository.insert(transaction);
    }
    public Collection<Transaction> getTransactionsForWallet(String walletId){
        System.out.println(walletId);
        System.out.println(transactionRepository.findByWalletId(walletId));
        return transactionRepository.findByWalletId(walletId);
    }
}
