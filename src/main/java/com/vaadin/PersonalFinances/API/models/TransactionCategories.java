package com.vaadin.PersonalFinances.API.models;

import java.util.ArrayList;
import java.util.List;

public class TransactionCategories {
    private List<String> categories;
    public TransactionCategories(){
        categories = new ArrayList<>();
        this.categories.add("Housing");
        this.categories.add("Transport");
        this.categories.add("Food");
        this.categories.add("Clothing");
        this.categories.add("Entertainment");
        this.categories.add("Electronics");
        this.categories.add("Healthcare");
        this.categories.add("Recreation");

    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
