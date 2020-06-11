package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.WalletRepository;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionType;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWalletService {
    @Autowired
    WalletService walletService;
    @MockBean
    WalletRepository walletRepository;


    @Test
    public void getWallet() throws Exception{

        Optional<Wallet> wallet = Optional.of(new Wallet());
        wallet.get().setCurrency("test");
        when(walletRepository.findById("walletId")).thenReturn(wallet);
        Optional<Wallet> afterUpdate = walletService.getWallet("walletId");


        assertEquals("test", afterUpdate.get().getCurrency());
    }

    @Test
    public void updateBalanceExpense() throws Exception{

        Optional<Wallet> wallet = Optional.of(new Wallet());
        when(walletRepository.findById("walletId")).thenReturn(wallet);
        Optional<Wallet> afterUpdate = walletService.updateBalance(new Transaction("title", "category", new Date(), TransactionType.EXPENSE.toString(), BigDecimal.valueOf(10.0000001), "walletId"));

        BigDecimal balance = new BigDecimal(-10);
        balance = balance.setScale(2, RoundingMode.DOWN);

        assertEquals(balance, afterUpdate.get().getBalance());
    }

    @Test
    public void updateBalanceIncome() throws Exception{

        Optional<Wallet> wallet = Optional.of(new Wallet());
        when(walletRepository.findById("walletId")).thenReturn(wallet);
        Optional<Wallet> afterUpdate = walletService.updateBalance(new Transaction("title", "category", new Date(), TransactionType.INCOME.toString(), BigDecimal.valueOf(10.0000001), "walletId"));

        BigDecimal balance = new BigDecimal(10);
        balance = balance.setScale(2, RoundingMode.DOWN);

        assertEquals(balance, afterUpdate.get().getBalance());
    }


}
