package com.vaadin.PersonalFinances.API.Controllers;

import com.vaadin.PersonalFinances.API.ErrorHandling.CurrencyExchangeError;
import com.vaadin.PersonalFinances.API.ErrorHandling.StatisticsError;
import com.vaadin.PersonalFinances.API.ErrorHandling.TransactionError;
import com.vaadin.PersonalFinances.API.ErrorHandling.WalletError;
import com.vaadin.PersonalFinances.API.Services.StatisticsService;
import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<Transaction>> getWalletTransactions(@PathVariable("walletId") String walletId){
        try{
            //System.out.println("wallet controller: "+ walletId );
            return new ResponseEntity<>(transactionService.getTransactionsForWallet(walletId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(" WalletController: Exception: getWalletTransactions: "+ e.getMessage());
            return new ResponseEntity<>(new TransactionError().getTransactionListErrorBody(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/{walletId}/transactions")
    public ResponseEntity<Transaction>  postWalletTransaction(@PathVariable("walletId") String walletId, @RequestBody Transaction transaction){
        try{
            transaction.setWalletId(walletId);
            transaction.setCurrency(walletService.getWallet(walletId).get().getCurrency());
            return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(" WalletController: Exception: postWalletTransaction: "+ e.getMessage());
            return new ResponseEntity<>(new TransactionError().getTransactionErrorBody(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/{walletId}/statistics/expense")
    public ResponseEntity<Statistics> getExpenseStatisticsForWallet(@PathVariable("walletId") String walletId){
        try{
            return new ResponseEntity<>(statisticsService.getExpenseStatisticsForWallet(walletId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(" WalletController: Exception: getIncomeStatisticsForWallet: "+ e.getMessage());
            return new ResponseEntity<>(new StatisticsError().getStatiticsErrorBody(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/{walletId}/statistics/income")
    public ResponseEntity<Statistics> getIncomeStatisticsForWallet(@PathVariable("walletId") String walletId){
        try{
            return new ResponseEntity<>(statisticsService.getIncomeStatisticsForWallet(walletId), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(" WalletController: Exception: getIncomeStatisticsForWallet: "+ e.getMessage());
            return new ResponseEntity<>(new StatisticsError().getStatiticsErrorBody(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/{walletId}/changeCurrency/{newCurrency}")
    public ResponseEntity<Wallet> changeCurrency(@PathVariable("walletId") String walletId, @PathVariable("newCurrency") String newCurrency){
        //System.out.println("WalletController: changeCurrency: "+walletId+" "+ newCurrency);

        try{
            return new ResponseEntity<>(walletService.changeWalletCurrency(walletId, newCurrency).get(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(" WalletController: Exception:changeCurrency: "+ e.getMessage());
            return  new ResponseEntity<>(new WalletError().getErrorBody(), HttpStatus.BAD_REQUEST);
        }

    }

}
