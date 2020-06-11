package com.vaadin.PersonalFinances.API.Controllers;

import com.vaadin.PersonalFinances.API.ErrorHandling.CurrencyExchangeError;
import com.vaadin.PersonalFinances.API.Services.CurrencyExchangeService;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency/{baseCurrency}")
public class CurrencyExchangeController {
    @Autowired
    CurrencyExchangeService currencyExchangeService;
    @GetMapping
    public ResponseEntity<ExchangeRatesInformation> getExchangeValues(@PathVariable("baseCurrency") String baseCurrency){
        try {
            return new ResponseEntity<>(currencyExchangeService.getCurrencyExchangeRatesInformation(baseCurrency), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("CurrencyExchangeService: getCurrencyExchangeRatesInformation: Exception: https://api.exchangeratesapi.io: " + e.getMessage());
            return  new ResponseEntity<>(new CurrencyExchangeError().getErrorBody(), HttpStatus.BAD_REQUEST);
        }

    }
}
