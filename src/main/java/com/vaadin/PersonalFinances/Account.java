package com.vaadin.PersonalFinances;

public  class Account {
    static int balance;

    public Account(){
        balance = 0;
    }
    public void setBalance(int value){
        balance +=value;
    }
    public int getBalance(){
        return balance;
    }
}
