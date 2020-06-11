package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.API.Services.StatisticsService;
import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStatisticsService {
    @Autowired
    StatisticsService statisticsService;

    @MockBean
    TransactionRepository transactionRepository;

    @Test
    public void getExpenseStatisticForWalletNumber(){
        Transaction transaction = new Transaction();
        transaction.setCategory(new TransactionExpenseCategories().getCategories().get(0));
        transaction.setAmountOfMoney(BigDecimal.valueOf(10));
        when(transactionRepository.findByWalletId("walletId")).thenReturn(Stream.of(transaction).collect(Collectors.toList()));

        assertEquals(1,statisticsService.getExpenseStatisticsForWallet("walletId").getNumberByCategory()[0]);
        assertEquals(0,statisticsService.getExpenseStatisticsForWallet("walletId").getNumberByCategory()[2]);
    }
    @Test
    public void getExpenseStatisticForWalletMoney(){
        Transaction transaction = new Transaction();
        transaction.setCategory(new TransactionExpenseCategories().getCategories().get(0));
        transaction.setAmountOfMoney(BigDecimal.valueOf(10));
        when(transactionRepository.findByWalletId("walletId")).thenReturn(Stream.of(transaction).collect(Collectors.toList()));

        assertEquals(BigDecimal.valueOf(10),statisticsService.getExpenseStatisticsForWallet("walletId").getMoneyByCategory()[0]);
    }
    /*
    private List<Transaction> getTransactionList(){

    }*/
}
