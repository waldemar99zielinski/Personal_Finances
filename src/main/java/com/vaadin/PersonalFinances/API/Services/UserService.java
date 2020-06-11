package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.Repositories.UserRepository;
import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletService walletService;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUser(String id){
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        Wallet newWallet = walletService.createWallet();
        user.setWalletId(newWallet.getId());
        return userRepository.insert(user);
    }

}