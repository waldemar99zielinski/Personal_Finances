package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.UserRepository;
import com.vaadin.PersonalFinances.API.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        return userRepository.insert(user);
    }

}