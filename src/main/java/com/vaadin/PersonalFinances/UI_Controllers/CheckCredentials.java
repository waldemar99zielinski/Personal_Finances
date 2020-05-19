package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.views.elements.LayoutLogin;

public class CheckCredentials {
    UserCredentialsValidationService service;
    LayoutLogin layoutLogin;
    public CheckCredentials(){
        layoutLogin = new LayoutLogin();
        service = new UserCredentialsValidationService();
        if(!service.areCredentialsValid()){
            layoutLogin.dialogAction();
        }
    }
}
