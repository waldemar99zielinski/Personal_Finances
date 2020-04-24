package com.vaadin.PersonalFinances.API.Repositories;

import com.vaadin.PersonalFinances.API.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public Collection<Transaction> findByWalletId(String walletId);
}
