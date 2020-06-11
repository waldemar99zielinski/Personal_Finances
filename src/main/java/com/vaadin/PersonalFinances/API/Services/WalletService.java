package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository repository;
    @Autowired
    private CurrencyExchangeService currencyExchangeService;


    public Collection<Wallet> getWallets() {
        return repository.findAll();
    }

    public Wallet createWallet() {

        return repository.insert(new Wallet());
    }
    public Optional<Wallet> getWallet(String id){

        return repository.findById(id);
    }
    public Optional<Wallet> deleteWallet(String id){
        Optional<Wallet> wallet = repository.findById(id);
        if(wallet.isPresent()){
            repository.deleteById(id);
        }
        return wallet;
    }
    public Optional<Wallet> updateBalance(Transaction transaction){
        Optional<Wallet> walletToUpdate = repository.findById(transaction.getWalletId());
        if(walletToUpdate.isPresent()){

            walletToUpdate.get().setBalanceAddingTransaction(transaction);
           
            repository.save(walletToUpdate.get());
        }
        return walletToUpdate;
    }
    public Optional<Wallet> changeWalletCurrency(String walletId, String currencyToChange){
        Optional<Wallet> walletToUpdate = repository.findById(walletId);
        //System.out.println(walletToUpdate.isPresent());
        if(walletToUpdate.isPresent()){
            ExchangeRatesInformation exchangeRatesInformation = currencyExchangeService.getCurrencyExchangeRatesInformation(walletToUpdate.get().getCurrency());
            //System.out.println("WalletService: changeWalletCurrency"+"USD: "+exchangeRatesInformation.getRates().getUSD() + " PLN: "+exchangeRatesInformation.getRates().getPLN());
            String currentCurrency = walletToUpdate.get().getCurrency();
            //System.out.println(exchangeRatesInformation.getRates().getExchangeValue(currencyToChange));
            walletToUpdate.get().changeCurrency(currencyToChange, exchangeRatesInformation.getRates().getExchangeValue(currencyToChange));
            //System.out.println("WalletService: changeWalletCurrency: after changeCurrency : wallet getBalace : "+walletToUpdate.get().getBalance()+ walletToUpdate.get().getCurrency());
            repository.save(walletToUpdate.get());
        }
        return walletToUpdate;
    }
}
