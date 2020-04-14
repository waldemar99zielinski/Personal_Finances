package com.vaadin.PersonalFinances.Repositories;

import com.vaadin.PersonalFinances.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
