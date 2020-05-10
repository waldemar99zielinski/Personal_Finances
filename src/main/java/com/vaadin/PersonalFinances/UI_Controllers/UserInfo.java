package com.vaadin.PersonalFinances.UI_Controllers;

public class UserInfo {
    private String userId;
    private String walletId;

    public UserInfo(){
        this.walletId = "5ea408cafeb8764046ad7de2";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void check(){
        
    }
}
