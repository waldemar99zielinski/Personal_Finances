package com.vaadin.PersonalFinances.API.Controllers;


import com.vaadin.PersonalFinances.API.ErrorHandling.UserError;
import com.vaadin.PersonalFinances.API.Services.UserService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.atmosphere.config.service.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;

    @GetMapping
    public Collection<User> getUser(){

        return userService.getUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(userService.getUser(id).get(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("UserController: getUser: Exception: " + e.getMessage());
            return new ResponseEntity<>(new UserError().getErrorBody(), HttpStatus.NOT_FOUND);
        }

    }






}
