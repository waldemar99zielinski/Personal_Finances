package com.vaadin.PersonalFinances.API.Repositories;

import com.vaadin.PersonalFinances.API.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
