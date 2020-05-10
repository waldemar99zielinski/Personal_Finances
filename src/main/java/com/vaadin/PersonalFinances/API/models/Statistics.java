package com.vaadin.PersonalFinances.API.models;

import java.math.BigDecimal;

public class Statistics {
    private int[] numberByCategory;

    private BigDecimal[] moneyByCategory;

    public Statistics(){

    }

    public int[] getNumberByCategory() {
        return numberByCategory;
    }

    public void setNumberByCategory(int[] numberByCategory) {
        this.numberByCategory = numberByCategory;
    }

    public BigDecimal[] getMoneyByCategory() {
        return moneyByCategory;
    }

    public void setMoneyByCategory(BigDecimal[] moneyByCategory) {
        this.moneyByCategory = moneyByCategory;
    }
}
