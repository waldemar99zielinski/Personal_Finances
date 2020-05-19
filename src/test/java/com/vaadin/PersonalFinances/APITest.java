package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.API.Repositories.UserRepository;
import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.Services.UserService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APITest {


    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;


    @Test
    public void getUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new User(), new User(), new User()).collect(Collectors.toList()));
        assertEquals(3, userService.getUsers().size());
    }
    @Test
    public void createUser(){
        User testUser = new User("firstName", "lastName", "walletId");
        when(userRepository.insert(testUser)).thenReturn(testUser);
        assertEquals("firstName", userService.createUser(testUser).getFirstName());
        assertEquals("lastName", userService.createUser(testUser).getLastName());
    }
    @Test
    public void createTransaction(){
        Transaction testTransaction = new Transaction("title", "Food", new Date(), "expense", BigDecimal.valueOf(20), "walletId");
        when(transactionRepository.insert(testTransaction)).thenReturn(testTransaction);
        assertEquals("expense", transactionRepository.insert(testTransaction).getType());

    }
    @Test
    public void getTransactions(){
        when(transactionRepository.findAll()).thenReturn(Stream.of(new Transaction(), new Transaction()).collect(Collectors.toList()));
        assertEquals(2, transactionService.getTransactions().size());

    }






}
