package com.vaadin.PersonalFinances.API.Controllers;


import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Collection<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }


}
