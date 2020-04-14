package com.vaadin.PersonalFinances.Controllers;


import com.vaadin.PersonalFinances.Services.TransactionService;
import com.vaadin.PersonalFinances.Services.UserService;
import com.vaadin.PersonalFinances.models.Transaction;
import com.vaadin.PersonalFinances.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Collection<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @PostMapping
    public Transaction postUser(@RequestBody Transaction transaction){
        Date date = new Date();
        transaction.setDate(date);
        return transactionService.createTransaction(transaction);
    }
}
