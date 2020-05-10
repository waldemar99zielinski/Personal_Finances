package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.atmosphere.config.service.Post;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class UI_Http_Service {

    private final RestTemplate restTemplate;
    private String UrlAPI = "http://localhost:8080/api/";
    private String UrlWallets = "wallets/";
    private String UrlUsers = "users/";
    private String UrlTransactions = "transactions/";
    private String UrlStatistics = "statistics/";
    private String UrlCookieSet = "users/setCookies/";

    public UI_Http_Service(){
        this.restTemplate = new RestTemplate();
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
    public List<Transaction> getWalletTransactions(String WalletId)
    {
        String url = UrlAPI + UrlWallets +WalletId + "/" + UrlTransactions;
        //System.out.println("FULL URLS:"+ fullUrl);

        Transaction[] response = restTemplate.getForObject(url, Transaction[].class);

        List<Transaction> transactionsList = Arrays.asList(response);

        //System.out.println("dlusgosc: "+ walletsList.size()+" ");

        return transactionsList;
    }
    public Statistics getStatisticsForWallet()
    {
        String url = UrlAPI + UrlWallets +new UserInfo().getWalletId() + "/" + UrlStatistics;
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

        HttpEntity<User> enity = new HttpEntity<>(user, headers);

        return restTemplate.postForObject(url, enity, User.class);
    }
    public void setCookies(User user){
        String url = UrlAPI + "/users/change-username"; //+ user.getId();
        System.out.println("setCookies UI_");



        restTemplate.getForObject(url, String.class);
    }

}
