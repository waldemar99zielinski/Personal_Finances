package com.vaadin.PersonalFinances.API.models;

import java.util.ArrayList;
import java.util.List;

public class TransactionIncomeCategories {
    private List<String> categories;
    public TransactionIncomeCategories(){
        categories = new ArrayList<>();
        this.categories.add("Active");
        this.categories.add("Passive");
        this.categories.add("Portfolio");


    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
