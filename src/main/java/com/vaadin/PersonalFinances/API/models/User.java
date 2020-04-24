package com.vaadin.PersonalFinances.API.models;

//package com.example.accessingdatamongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {


    @Id
    public String id;

    private String firstName;
    private String lastName;

    private String walletId;

    public User(String firstName, String lastName, String walletId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }


}