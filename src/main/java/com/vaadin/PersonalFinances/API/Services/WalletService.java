package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
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

    public Wallet createWallet(Wallet wallet) {

        return repository.insert(wallet);
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
    public Optional<Wallet> updateBalance(String id, BigDecimal amount){
        Optional<Wallet> walletToUpdate = repository.findById(id);
        if(walletToUpdate.isPresent()){

            walletToUpdate.get().setBalanceAddingTransaction(amount);
           
            repository.save(walletToUpdate.get());
        }
        return walletToUpdate;
    }

}
