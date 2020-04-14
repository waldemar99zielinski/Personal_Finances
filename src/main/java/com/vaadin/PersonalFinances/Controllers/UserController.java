package com.vaadin.PersonalFinances.Controllers;


import com.vaadin.PersonalFinances.Services.UserService;
import com.vaadin.PersonalFinances.models.User;
import org.atmosphere.config.service.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getUsers(){

        return userService.getUsers();
    }

    @PostMapping
    public User postUser(@RequestBody User user){

        return userService.createUser(user);
    }
}
