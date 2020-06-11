package com.vaadin.PersonalFinances.API.Services;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.PersonalFinances.API.ErrorHandling.CurrencyExchangeError;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeService {
    private String url = "https://api.exchangeratesapi.io/latest?symbols=PLN,USD,GBP,CNY&base=";

    RestTemplate restTemplate;

    public CurrencyExchangeService(){
        //restTemplate = new getRestTemplate();
    }

    public ExchangeRatesInformation getCurrencyExchangeRatesInformation(String baseCurrency){

            String urlWithBaseCurrency = url + baseCurrency;

            ExchangeRatesInformation exchange = getRestTemplate().getForObject(urlWithBaseCurrency, ExchangeRatesInformation.class);
            //System.out.println("CurrencyExchangeService: ExchangeRatesInformation url:"+urlWithBaseCurrency + exchange.getRates().getPLN() + " "+exchange.getRates().getUSD());
            return exchange;

    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper;
    }


    private MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }


    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, mappingJacksonHttpMessageConverter());
        return restTemplate;
    }
}
