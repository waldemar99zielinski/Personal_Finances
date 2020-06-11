package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.web.client.RestTemplate;

public class UserCredentialsValidationService {
    private final RestTemplate restTemplate;
    private final String UrlAPI = "http://localhost:8080/api/";
    private final String UrlWallets = "wallets/";
    private final String UrlUsers = "users/";
    private UserInfo userInfo;
    public UserCredentialsValidationService(){
        restTemplate = new RestTemplate();
        userInfo = new UserInfo();
    }
    private Wallet getWallet(String id){
        String url = UrlAPI + UrlWallets +id;

        Wallet response = restTemplate.getForObject(url, Wallet.class);



        return response;
    }
    private User getUser(String userId){
        String url = UrlAPI + UrlUsers + userId;

        User response = restTemplate.getForObject(url, User.class);

        return response;
    }
    public boolean areCredentialsValid(){
        try{
            userInfo.loadCredentials();
            String userId = userInfo.getUserId();
            String walletId = userInfo.getWalletId();
            User user = getUser(userId);
            if(walletId.equals(user.getWalletId())){
                Wallet wallet = getWallet(walletId);
            }else {
                return  false;
            }
            return true;
        }catch (Exception e){
            System.out.println("UserCredentialsValidationService: areCredentialsValid: "+e.getMessage());
            return false;
        }
    }
}
