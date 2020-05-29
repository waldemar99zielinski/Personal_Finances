package com.vaadin.PersonalFinances.API.models.currencyModels;

import java.math.BigDecimal;

public class Rates {

    private float GBP;

    private float CNY;

    private float USD;

    private float PLN;

    public Rates(){

    }
    // Getter Methods


    public float getGBP() {
        return GBP;
    }

    public float getCNY() {
        return CNY;
    }


    public float getUSD() {
        return USD;
    }



    public float getPLN() {
        return PLN;
    }

    public BigDecimal getExchangeValue(String exchangeCurrency){
        switch (exchangeCurrency){
            case "USD":
                return BigDecimal.valueOf(USD);
            case "PLN":
                return BigDecimal.valueOf(PLN);
            case "GBP":
                return BigDecimal.valueOf(GBP);
            case "CNY":
                return BigDecimal.valueOf(CNY);
            default:
                return BigDecimal.valueOf(Integer.MIN_VALUE);

        }
    }



    public void setGBP(float GBP) {
        this.GBP = GBP;
    }



    public void setCNY(float CNY) {
        this.CNY = CNY;
    }



    public void setUSD(float USD) {
        this.USD = USD;
    }



    public void setPLN(float PLN) {
        this.PLN = PLN;
    }
}