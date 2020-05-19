package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.UI_Controllers.UI_WalletController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.math.BigDecimal;
import java.util.Optional;

@CssImport("./styles/style.css")
public class LayoutWalletOverview {
    UI_WalletController walletController;
    public LayoutWalletOverview(){
        this.walletController = new UI_WalletController();
    }
    public VerticalLayout returnLayout(){
        VerticalLayout main = new VerticalLayout();
        main.setSizeFull();


        HorizontalLayout h1 = new HorizontalLayout();
        h1.setSizeFull();
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setSizeFull();


        h1.add(panel(), userPanel());
        h2.add(panel(),panel());
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

        Label money = new Label(  getBalance() + " $");
        money.addClassName("money");
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
    BigDecimal getBalance(){
        try{
            return walletController.getWalletBalance();
        }catch (Exception e){

            return BigDecimal.ZERO;
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
}
