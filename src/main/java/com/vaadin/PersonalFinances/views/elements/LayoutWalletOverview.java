package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.API.models.currencyModels.Currencies;
import com.vaadin.PersonalFinances.API.models.currencyModels.ExchangeRatesInformation;
import com.vaadin.PersonalFinances.UI_Controllers.UI_CurrencyExchangeService;
import com.vaadin.PersonalFinances.UI_Controllers.UI_UserController;
import com.vaadin.PersonalFinances.UI_Controllers.UI_WalletController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;

import java.math.BigDecimal;
import java.util.Optional;

@CssImport("./styles/style.css")
public class LayoutWalletOverview {
    UI_WalletController walletController;
    Wallet wallet;
    UI_UserController userController;
    public LayoutWalletOverview(){
        walletController = new UI_WalletController();
        userController = new UI_UserController();
        wallet = getWallet();
    }
    public VerticalLayout returnLayout(){
        VerticalLayout main = new VerticalLayout();
        main.setSizeFull();


        HorizontalLayout h1 = new HorizontalLayout();
        h1.setSizeFull();
        h1.setHeight("50%");
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setSizeFull();
        h2.setHeight("50%");


        h1.add(panel(), userPanel());
        h2.add(currencyExchangePanel(),currencyExchangeValuesPanel());
        main.add(h1,h2);

        return main;
    }
    HorizontalLayout horizontal(){
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        return horizontalLayout;
    }
    VerticalLayout panel(){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addClassName("panel");

        Label accountBalanceLabel = new Label("Account balance:");
        accountBalanceLabel.addClassName("accountBalace");

        BigDecimal balance = getBalance();
        Label money = new Label(  balance+ " " + getCurrency());
        //set balance color to red if less than 0
        if(balance.compareTo(BigDecimal.ZERO) == -1){
            money.addClassName("negativeMoney");
        }else{
            money.addClassName("money");
        }

        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.END, money);
        verticalLayout.add(accountBalanceLabel, money);

        return verticalLayout;
    }
    VerticalLayout userPanel(){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addClassName("panel");

        Label userinfo = new Label("User info:");
        userinfo.addClassName("accountBalace");

        Label firstName = new Label(  getFirstName());
        firstName.addClassName("userinfo");

        Label lastName = new Label(  getLastName());
        lastName.addClassName("userinfo");

        verticalLayout.add(userinfo, firstName, lastName);

        return verticalLayout;
    }
    VerticalLayout currencyExchangePanel(){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addClassName("panel");

        Label currencyLabel = new Label("Currency");
        currencyLabel.addClassName("accountBalace");

        Label currentCurrencyLabel = new Label("current: " + getCurrency());
        currentCurrencyLabel.addClassName("userinfo");

        Label convertCurrencyLabel = new Label("convert:");
        convertCurrencyLabel.addClassName("userinfo");

        Select<Currencies> currencySelect = new Select<>();
        currencySelect.setItems(Currencies.values());
        currencySelect.setValue(Currencies.valueOf(getCurrency()));

        Button convertButton = new Button("Convert");
        convertButton.addClickListener(event -> {
            convertCurrencyButtonAction(currencySelect.getValue().toString());
        });


        verticalLayout.add(currencyLabel, currentCurrencyLabel, convertCurrencyLabel, new HorizontalLayout(currencySelect, convertButton));

        return verticalLayout;
    }
    VerticalLayout currencyExchangeValuesPanel(){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addClassName("panel");

        ExchangeRatesInformation exchangeRatesInformation = new UI_CurrencyExchangeService().getExchangeRatesInformation();

        Label currencyLabel = new Label("Exchange rates");
        currencyLabel.addClassName("accountBalace");

        Label cnyLabel = new Label("CNY: "+ exchangeRatesInformation.getRates().getCNY());
        cnyLabel.addClassName("userinfo");

        Label gbpLabel = new Label("GBP: "+ exchangeRatesInformation.getRates().getGBP());
        gbpLabel.addClassName("userinfo");

        Label usdLabel = new Label("USD: "+ exchangeRatesInformation.getRates().getUSD());
        usdLabel.addClassName("userinfo");

        Label plnLabel = new Label("PLN: "+ exchangeRatesInformation.getRates().getPLN());
        plnLabel.addClassName("userinfo");



        verticalLayout.add(currencyLabel,cnyLabel,gbpLabel,usdLabel,plnLabel);

        return verticalLayout;
    }
    BigDecimal getBalance(){

            return walletController.getWallet().getBalance();


    }
    String getCurrency(){

            return walletController.getWallet().getCurrency();
    }
    String getFirstName(){

            return userController.getUser().getFirstName();

    }
    String getLastName(){
        return userController.getUser().getLastName();
    }
    Wallet getWallet(){

            return walletController.getWallet();

    }
    void convertCurrencyButtonAction(String newCurrency){
        walletController.changeWalletCurrency(newCurrency);
        UI.getCurrent().getPage().reload();
    }
}
