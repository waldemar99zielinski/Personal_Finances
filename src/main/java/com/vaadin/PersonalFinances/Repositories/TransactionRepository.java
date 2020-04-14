package com.vaadin.PersonalFinances.Repositories;

import com.vaadin.PersonalFinances.models.Transaction;
import com.vaadin.PersonalFinances.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public Collection<Transaction> findByWalletId(String walletId);
}
