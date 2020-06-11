package com.vaadin.PersonalFinances.API.ErrorHandling;


import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import com.vaadin.PersonalFinances.API.models.currencyModels.Rates;

public class CurrencyExchangeError {
    public CurrencyExchangeError(){

    }
    public ExchangeRatesInformation getErrorBody(){
        ExchangeRatesInformation errorBody = new ExchangeRatesInformation();
        errorBody.setBase("null");
        errorBody.setDate(null);

        Rates rates = new Rates();
        rates.setPLN(0);
        rates.setUSD(0);
        rates.setGBP(0);
        rates.setCNY(0);
        errorBody.setRates(rates);

        return errorBody;
    }
}
