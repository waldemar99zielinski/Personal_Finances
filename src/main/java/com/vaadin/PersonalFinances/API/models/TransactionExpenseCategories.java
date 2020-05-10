package com.vaadin.PersonalFinances.API.models;

import java.util.ArrayList;
import java.util.List;

public class TransactionExpenseCategories {
    private List<String> categories;
    public TransactionExpenseCategories(){
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
    public String[] getCategoriesArray(){
        String[] array = new String[categories.size()];
        categories.toArray(array);

        return array;

    }
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
