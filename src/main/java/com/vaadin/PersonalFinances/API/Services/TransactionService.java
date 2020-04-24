package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletService walletService;

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        BigDecimal cost = transaction.getAmountOfMoney();
        String walletId = transaction.getWalletId();


        Optional<Wallet> wallet = walletService.updateBalance(walletId, transaction.getAmountOfMoney());


        //add exeptionHander
        return transactionRepository.insert(transaction);
    }
    public Collection<Transaction> getTransactionsForWallet(String walletId){


        return transactionRepository.findByWalletId(walletId);
    }
}
