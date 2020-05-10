package com.vaadin.PersonalFinances.API.Services;

import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private TransactionService transactionService;

    private TransactionExpenseCategories transactionExpenseCategories;


    public StatisticsService(){
        this.transactionService = new TransactionService();
        this.transactionExpenseCategories = new TransactionExpenseCategories();
    }
    public Statistics getExpenseStatisticsForWallet(String walletId){
        Statistics statistics = new Statistics();

        Collection<Transaction> transactionCollection = transactionService.getTransactionsForWallet(walletId);

        int[] numberByCategory = new int[8];

        BigDecimal[] moneyByCategory = new BigDecimal[8];
        Arrays.fill(moneyByCategory, BigDecimal.ZERO);

        for(Transaction t : transactionCollection){
            for(int tCategory = 0; tCategory < transactionExpenseCategories.getCategoriesArray().length; tCategory++){

                if(t.getCategory().equals(transactionExpenseCategories.getCategoriesArray()[tCategory]) ){

                    numberByCategory[tCategory]++;
                    moneyByCategory[tCategory] =  moneyByCategory[tCategory].add(t.getAmountOfMoney());

                }
            }
        }

        statistics.setNumberByCategory(numberByCategory);
        statistics.setMoneyByCategory(moneyByCategory);

        return statistics;
    }

}
