package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class UserInfo {
    private String userId;
    private String walletId;



    public UserInfo(){


        loadCredentials();
    }
    public void setUser(User user){
        this.userId = user.getId();
        this.walletId = user.getWalletId();
    }

    public String getUserId() {

            loadCredentials();

            return userId;


    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletId() {
        loadCredentials();
        return walletId;
    }

    public void setWalletId(String walletId) {

        this.walletId = walletId;
    }

    public void loadCredentials(){
        try{
            File file = new File("credentials.txt");
            Scanner scanner = new Scanner(file);
            userId = scanner.nextLine();
            walletId = scanner.nextLine();
            scanner.close();
            //System.out.println("userInfo: loadCredentials:" +userId + " "+walletId);
        }catch (IOException e){
            userId = "null";
            walletId = "null";
            System.out.println("Userinfo: loadCredentials: Exception: "+ e.getMessage());
        }
    }
    public void setCredentials(User user){
        try{
            FileWriter fileWriter = new FileWriter("credentials.txt");
            fileWriter.write(user.getId()+ "\n" + user.getWalletId());
            fileWriter.close();
        }catch (IOException e){
            System.out.println("userInfo: setCredentials: Exception: "+e.getMessage());
        }
    }
}
