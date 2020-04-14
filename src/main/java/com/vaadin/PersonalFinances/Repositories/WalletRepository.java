package com.vaadin.PersonalFinances.Repositories;

import com.vaadin.PersonalFinances.models.User;
import com.vaadin.PersonalFinances.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, String> {

}
