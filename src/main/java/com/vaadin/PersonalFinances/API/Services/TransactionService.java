package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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



        Optional<Wallet> wallet = walletService.updateBalance(transaction);



        return transactionRepository.insert(transaction);
    }
    public Collection<Transaction> getTransactionsForWallet(String walletId){
       // List<Transaction> transactionList = //transactionRepository.findByWalletId(walletId);

        return transactionRepository.findByWalletId(walletId);
    }

}
