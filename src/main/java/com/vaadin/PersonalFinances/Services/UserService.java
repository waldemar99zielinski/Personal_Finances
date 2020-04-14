package com.vaadin.PersonalFinances.Services;

import com.vaadin.PersonalFinances.Repositories.UserRepository;
import com.vaadin.PersonalFinances.models.User;
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