package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.StyleSheet;
import java.util.*;


@Service
public class UI_Http_Service {

    private final RestTemplate restTemplate;
    private final String UrlAPI = "http://localhost:8080/api/";
    private final String UrlWallets = "wallets/";
    private final String UrlUsers = "users/";
    private final String UrlTransactions = "transactions/";
    private final String UrlStatistics = "statistics/";
    private final String UrlCookieSet = "users/setCookies/";
    private UserInfo userInfo;
    public UI_Http_Service(){
        this.restTemplate = new RestTemplate();
        userInfo = new UserInfo();
    }

    public List<Wallet> getAllWallets()
    {
        String url = UrlAPI + UrlWallets;
        //System.out.println("FULL URLS:"+ fullUrl);

      Wallet[] response = restTemplate.getForObject(url, Wallet[].class);

      List<Wallet> walletsList = Arrays.asList(response);

        //System.out.println("dlusgosc: "+ walletsList.size()+" ");

        return walletsList;
    }
    public Wallet getWallet(String id){
        String url = UrlAPI + UrlWallets +id;

        Wallet response = restTemplate.getForObject(url, Wallet.class);


       return response;
    }
    public Wallet postWallet(Wallet wallet){
        String url = UrlAPI + UrlWallets;

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Wallet> enity = new HttpEntity<>(wallet, headers);

        return restTemplate.postForObject(url, enity, Wallet.class);
    }
    public Transaction postWalletTransaction(Transaction transaction, String WalletId){
        String url = UrlAPI + UrlWallets +WalletId + "/transactions";

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Transaction> enity = new HttpEntity<>(transaction, headers);

        return restTemplate.postForObject(url, enity, Transaction.class);
    }
    public List<Transaction> getExpenseWalletTransactions()
    {
        String WalletId = userInfo.getWalletId();
        String url = UrlAPI + UrlWallets +WalletId + "/" + UrlTransactions + "expense";
        //System.out.println("FULL URLS:"+ fullUrl);

        Transaction[] response = restTemplate.getForObject(url, Transaction[].class);

        List<Transaction> transactionsList = Arrays.asList(response);

        //System.out.println("dlusgosc: "+ walletsList.size()+" ");

        return transactionsList;
    }public List<Transaction> getIncomeWalletTransactions()
    {
        String WalletId = userInfo.getWalletId();
        String url = UrlAPI + UrlWallets +WalletId + "/" + UrlTransactions + "income";
        //System.out.println("FULL URLS:"+ fullUrl);

        Transaction[] response = restTemplate.getForObject(url, Transaction[].class);

        List<Transaction> transactionsList = Arrays.asList(response);

        //System.out.println("dlusgosc: "+ walletsList.size()+" ");

        return transactionsList;
    }
    public Statistics getExpenseStatisticsForWallet()
    {
        String url = UrlAPI + UrlWallets +new UserInfo().getWalletId() + "/" + UrlStatistics + "expense";
        //System.out.println("FULL URLS:"+ url);

        Statistics response = restTemplate.getForObject(url, Statistics.class);



        return response;
    }
    public Statistics getIncomeStatisticsForWallet()
    {
        String url = UrlAPI + UrlWallets +new UserInfo().getWalletId() + "/" + UrlStatistics + "income";
        //System.out.println("FULL URLS:"+ url);

        Statistics response = restTemplate.getForObject(url, Statistics.class);



        return response;
    }
    public List<User> getAllUsers()
    {
        String url = UrlAPI + UrlUsers;
        //System.out.println("FULL URLS:"+ url);

        User[] response = restTemplate.getForObject(url, User[].class);

        List<User> usersList = Arrays.asList(response);

        //System.out.println("dlusgosc: "+ walletsList.size()+" ");

        return usersList;
    }
    public User getUser(String userId){
        String url = UrlAPI + UrlUsers + userId;

        User response = restTemplate.getForObject(url, User.class);

        return response;
    }
    /*
    public void getOne(String endPoint, String id)
    {
        String fullUrl = APIurl + endPoint;
        //System.out.println("FULL URLS:"+ fullUrl);

        String result = restTemplate.getForObject(fullUrl, String.class);

        System.out.println(result);
    }
    public void post(String object){

    }*/
    public User postUser(User user){
        String url = UrlAPI + UrlUsers;

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.postForObject(url, entity, User.class);
    }
    public Wallet changeWalletCurrency(String walletId, String currencyToChange){
        String url = UrlAPI + UrlWallets + walletId + "/changeCurrency/"+currencyToChange;
        System.out.println("UI_Http: changeWalletCurrency: "+url);
        Wallet response = restTemplate.getForObject(url, Wallet.class);

        return response;

    }
}
