package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository repository;

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

}
