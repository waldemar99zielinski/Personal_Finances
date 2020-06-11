package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.UI_Controllers.UserInfo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;




public class LayoutLogin {

    UI_Http_Service service;
    UserInfo userInfo;

    Grid<User> grid_users;
    TextField textField_userFirstName;
    TextField textField_userLastName;

    String userFirstName;
    String userLastName;

    Button button_Signin;
    Button button_clear;

    Dialog dialog;

    boolean isCurrentSignIn;
    public LayoutLogin(){
        service = new UI_Http_Service();
        userInfo = new UserInfo();
        grid_users = new Grid<>();

        textField_userFirstName = new TextField();
        textField_userFirstName.setLabel("First name");
        textField_userLastName = new TextField();
        textField_userLastName.setLabel("Last name");
        button_clear =new Button("Clear");
        button_Signin = new Button("Sign in");

        isCurrentSignIn = false;

        dialog = new Dialog();



    }
    public void dialogAction(){


        dialog.setSizeFull();

        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        dialog.addDialogCloseActionListener(event -> {

        });

        dialog.setHeight("520px");
        dialog.setWidth("480px");
        dialog.removeAll();
        if(isCurrentSignIn){
            dialog.add(layoutSignIn());
        }else{
            dialog.add(layoutSelectUser());
        }

        dialog.open();

    }
    public VerticalLayout getLayout(){
        VerticalLayout layout = new VerticalLayout();

        layout.add(layoutSignIn());
        layout.setSizeFull();
        return layout;
    }
    private VerticalLayout layoutSignIn(){
        VerticalLayout layout = new VerticalLayout();
        layout.add(textField_userFirstName, textField_userLastName, button_Signin, button_clear);
        textField_userLastName.setWidth("75%");
        textField_userFirstName.setWidth("75%");
        button_Signin.setWidth("75%");
        button_clear.setWidth("75%");

        button_Signin.addClickListener(event -> buttonAction_signIn());

        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, textField_userFirstName);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, textField_userLastName);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, button_Signin);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, button_clear);


        layout.setSizeFull();
        return layout;
    }
    private VerticalLayout layoutSelectUser(){
        VerticalLayout layout = new VerticalLayout();
        grid_users.setItems(service.getAllUsers());
        grid_users.addColumn(User::getFirstName).setHeader("First name");
        grid_users.addColumn(User::getLastName).setHeader("Last name");

        grid_users.addItemClickListener(event -> {
            setUserCredentials(event.getItem());
            UI.getCurrent().getPage().reload();
            dialog.close();
        });

        Button button_switchToSignIn = new Button();
        button_switchToSignIn.setText("Sign in");
        button_switchToSignIn.setWidth("100%");
        button_switchToSignIn.addClickListener(event ->buttonAction_switchToSignIn(true));



        layout.add(grid_users, button_switchToSignIn);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, button_switchToSignIn);
        return layout;
    }

    private void buttonAction_switchToSignIn(boolean switchToSignIn){
        isCurrentSignIn = switchToSignIn;

        dialog.close();


        dialogAction();
    }
    private void buttonAction_signIn(){

        userFirstName = textField_userFirstName.getValue();
        userLastName = textField_userLastName.getValue();

        User newUser = new User();
        newUser.setFirstName(userFirstName);
        newUser.setLastName(userLastName);

        setUserCredentials(service.postUser(newUser));
        UI.getCurrent().getPage().reload();
        dialog.close();
    }
    private void setUserCredentials(User user){
        userInfo.setCredentials(user);
    }

}
