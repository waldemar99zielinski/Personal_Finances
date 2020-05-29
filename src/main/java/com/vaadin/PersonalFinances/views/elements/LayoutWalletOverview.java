package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.API.models.currencyModels.Currencies;
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
    public LayoutWalletOverview(){
        this.walletController = new UI_WalletController();
        this.wallet = getWallet();
    }
    public VerticalLayout returnLayout(){
        VerticalLayout main = new VerticalLayout();
        main.setSizeFull();


        HorizontalLayout h1 = new HorizontalLayout();
        h1.setSizeFull();
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setSizeFull();


        h1.add(panel(), userPanel());
        h2.add(currencyExchangePanel(),panel());
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

        Label currentCurrencyLabel = new Label("current: " + wallet.getCurrency());
        currentCurrencyLabel.addClassName("userinfo");

        Label convertCurrencyLabel = new Label("convert:");
        convertCurrencyLabel.addClassName("userinfo");

        Select<Currencies> currencySelect = new Select<>();
        currencySelect.setItems(Currencies.values());
        currencySelect.setValue(Currencies.valueOf(wallet.getCurrency()));

        Button convertButton = new Button("Convert");
        convertButton.addClickListener(event -> {
            convertCurrencyButtonAction(currencySelect.getValue().toString());
        });

        verticalLayout.add(currencyLabel, currentCurrencyLabel, convertCurrencyLabel, currencySelect, convertButton);

        return verticalLayout;
    }
    BigDecimal getBalance(){
        try{
            return walletController.getWalletBalance();
        }catch (Exception e){

            return BigDecimal.ZERO;
        }

    }
    String getCurrency(){
        try{
            return walletController.getWallet().getCurrency();
        }catch (Exception e){

            return null;
        }
    }
    String getFirstName(){
        try{
            return walletController.getFirstName();
        }catch (Exception e){
            return "null";
        }
    }
    String getLastName(){
        try{
            return walletController.getLastName();
        }catch (Exception e){
            return "null";
        }
    }
    Wallet getWallet(){
        try{
            return walletController.getWallet();
        }catch (Exception e){
            return null;
        }
    }
    void convertCurrencyButtonAction(String newCurrency){
        walletController.changeWalletCurrency(newCurrency);
        UI.getCurrent().getPage().reload();
    }
}
