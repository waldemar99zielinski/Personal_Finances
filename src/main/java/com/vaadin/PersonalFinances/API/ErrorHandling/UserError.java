package com.vaadin.PersonalFinances.API.ErrorHandling;

import com.vaadin.PersonalFinances.API.models.User;

public class UserError {
    public UserError(){

    }
    public User getErrorBody(){
        User user = new User();
        user.setFirstName("null");
        user.setLastName("null");
        return user;
    }
}
