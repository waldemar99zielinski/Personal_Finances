package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.TransactionRepository;
import com.vaadin.PersonalFinances.API.Repositories.UserRepository;
import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
import com.vaadin.PersonalFinances.API.Services.TransactionService;
import com.vaadin.PersonalFinances.API.Services.UserService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionType;
import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.API.models.currencyModels.Currencies;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWalletModel {


    @Test
    public void newWalletBalance () throws Exception{
        Wallet wallet = new Wallet();

        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }
    @Test
    public void newWalletCurrency()throws Exception{
        Wallet wallet = new Wallet();

        assertEquals(Currencies.USD.toString(), wallet.getCurrency());
    }
    @Test
    public void walletSetBalance()throws Exception{
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(10.123123123));

        assertEquals(BigDecimal.valueOf(10.12), wallet.getBalance());
    }
    @Test
    public void walletSetBalanceAddingTransactionIncome()throws Exception{
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(10.01));

        wallet.setBalanceAddingTransaction(new Transaction("title", "category", new Date(), TransactionType.INCOME.toString(), BigDecimal.valueOf(10.00), "walletId"));

        BigDecimal balance = new BigDecimal(20.01);
        balance = balance.setScale(2, RoundingMode.DOWN);

        assertEquals(balance, wallet.getBalance());
    }
    @Test
    public void walletSetBalanceAddingTransactionExpense()throws Exception{
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(10.01));

        wallet.setBalanceAddingTransaction(new Transaction("title", "category", new Date(), TransactionType.EXPENSE.toString(), BigDecimal.valueOf(10.00), "walletId"));

        BigDecimal balance = new BigDecimal(0.01);
        balance = balance.setScale(2, RoundingMode.DOWN);

        assertEquals(balance, wallet.getBalance());
    }

}
