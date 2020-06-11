package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
import com.vaadin.PersonalFinances.API.Services.CurrencyExchangeService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCurrencyExchangeWalletModel {


    @MockBean
    private CurrencyExchangeService currencyExchangeService;
    @MockBean
    private WalletRepository walletRepository;
    @MockBean
    private WalletService walletService;

    @Test
    public void walletModel_changeCurrency_NewCurrencyUSD() throws Exception {
        Wallet wallet = new Wallet();
        String newCurrency = "USD";


        wallet.changeCurrency(newCurrency, BigDecimal.valueOf(0));

        assertEquals(wallet.getCurrency(), "USD");


    }
    @Test
    public void walletModel_changeCurrency_NewCurrencyPLN() throws Exception {
        Wallet wallet = new Wallet();
        String newCurrency = "PLN";


        wallet.changeCurrency(newCurrency, BigDecimal.valueOf(0));

        assertEquals(wallet.getCurrency(), "PLN");


    }
    @Test
    public void walletModel_changeCurrency_NewBalance() throws Exception {
        Wallet wallet = new Wallet();

        BigDecimal initialBalance = BigDecimal.valueOf(10);
        BigDecimal exchangeValue = BigDecimal.valueOf(2.5);
        wallet.setBalance(initialBalance);
        wallet.changeCurrency("USD", exchangeValue);

        BigDecimal expected = BigDecimal.valueOf(25.00);
        expected = expected.setScale(2);

        assertEquals(expected, wallet.getBalance());
    }
    @Test
    public void walletModel_changeCurrency_NewBalanceZero() throws Exception {
        Wallet wallet = new Wallet();

        BigDecimal initialBalance = BigDecimal.valueOf(0);
        BigDecimal exchangeValue = BigDecimal.valueOf(2.5);
        wallet.setBalance(initialBalance);
        wallet.changeCurrency("USD", exchangeValue);

        BigDecimal expected = BigDecimal.valueOf(0);
        expected = expected.setScale(2);

        assertEquals(expected, wallet.getBalance());
    }
    @Test
    public void walletModel_changeCurrency_NewBalanceNegative() throws Exception {
        Wallet wallet = new Wallet();

        BigDecimal initialBalance = BigDecimal.valueOf(-2);
        BigDecimal exchangeValue = BigDecimal.valueOf(0.5);
        wallet.setBalance(initialBalance);
        wallet.changeCurrency("USD", exchangeValue);

        BigDecimal expected = BigDecimal.valueOf(-1);
        expected = expected.setScale(2);

        assertEquals(expected, wallet.getBalance());
    }

}
