package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.ErrorHandling.CurrencyExchangeError;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import org.springframework.web.client.RestTemplate;


public class UI_CurrencyExchangeService {
    private final RestTemplate restTemplate;
    private final String urlAPI = "http://localhost:8080/api/currency/";
    private final UI_Http_Service service;

    public UI_CurrencyExchangeService(){
        restTemplate = new RestTemplate();
        service = new UI_Http_Service();
    }
    public ExchangeRatesInformation getExchangeRatesInformation(){
        try{
            String base = service.getWallet(new UserInfo().getWalletId()).getCurrency();
            return restTemplate.getForObject(urlAPI + base, ExchangeRatesInformation.class);
        }catch (Exception e){
            return new CurrencyExchangeError().getErrorBody();
        }



    }
}
