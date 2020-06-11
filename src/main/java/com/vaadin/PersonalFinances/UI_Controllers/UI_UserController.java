package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.ErrorHandling.UserError;
import com.vaadin.PersonalFinances.API.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UI_UserController {
    private UserInfo userInfo;
    private final String urlApi = "http://localhost:8080/api/users/";
    private final RestTemplate restTemplate;

    public UI_UserController(){
        restTemplate = new RestTemplate();
        userInfo = new UserInfo();
    }
    public User getUser(){
        try{
            String url = urlApi + userInfo.getUserId();
           // System.out.println("UI_userController path:" + url);
            ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
            return response.getBody();
        }catch (Exception e){
            System.out.println("UI_userController: getUser: Exception:" + e.getMessage());
            return new UserError().getErrorBody();
        }

    }
}
