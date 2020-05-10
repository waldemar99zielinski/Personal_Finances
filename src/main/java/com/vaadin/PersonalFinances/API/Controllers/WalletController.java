package com.vaadin.PersonalFinances.API.Controllers;

import com.vaadin.PersonalFinances.API.Services.StatisticsService;
import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private StatisticsService statisticsService;
    @GetMapping
    public Collection<Wallet> getAllWallets(){
        return walletService.getWallets();
    }

    @PostMapping
    public Wallet postWallet(@RequestBody Wallet wallet){
        return walletService.createWallet();
    }

    @GetMapping(value = "/{id}")
    public Optional<Wallet> getWallet(@PathVariable("id") String id){
        return walletService.getWallet(id);
    }
    @DeleteMapping(value="/{id}")
    public Optional<Wallet> deleteWallet(@PathVariable("id") String id){
        return walletService.deleteWallet(id);
    }
    @GetMapping(value = "/{walletId}/transactions")
    public Collection<Transaction> getWalletTransactions(@PathVariable("walletId") String walletId){
        //System.out.println("wallet controller: "+ walletId );
        return transactionService.getTransactionsForWallet(walletId);
    }
    @PostMapping(value = "/{walletId}/transactions")
    public Transaction postWalletTransaction(@PathVariable("walletId") String walletId, @RequestBody Transaction transaction){

        transaction.setWalletId(walletId);

        return transactionService.createTransaction(transaction);
    }
    @GetMapping(value = "/{walletId}/statistics")
    public Statistics getStatisticsForWallet(@PathVariable("walletId") String walletId){
        return statisticsService.getExpenseStatisticsForWallet(walletId);
    }

}
