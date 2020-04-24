package com.vaadin.PersonalFinances.API.Repositories;

import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, String> {

}
