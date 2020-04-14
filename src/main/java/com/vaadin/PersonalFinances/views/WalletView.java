package com.vaadin.PersonalFinances.views;


import com.vaadin.PersonalFinances.Application;
import com.vaadin.PersonalFinances.Controllers.UserController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;



import java.awt.*;
import java.math.BigDecimal;

@Route(value = "", layout = MainView.class)
public class WalletView extends Div {
    BigDecimal balance;

    public WalletView() {
        balance = BigDecimal.ZERO;

        HorizontalLayout layoutAccuntValue = new HorizontalLayout();
        HorizontalLayout layoutAccountOperations = new HorizontalLayout();

        Paragraph paragraphAccountValue = new Paragraph("current account balance:" + balance + "$");



        BigDecimalField money = new BigDecimalField();
        money.setPlaceholder("Value");

        money.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        money.setPrefixComponent(new Icon(VaadinIcon.DOLLAR));



        Select<String> valueSelect = new Select<>();
        valueSelect.setItems("Income", "Expense");
        valueSelect.setValue("Income");

        Button buttonAdd = new Button("Add", event -> {
            /*if(valueSelect.getValue() == "Income"){
                balance = balance.add(money.getValue());
            }else{
                balance = balance.subtract(money.getValue());
            }

            paragraphAccountValue.setText("current account balance:" + balance + "$");

             */


        });
        buttonAdd.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_CONTRAST);

        layoutAccountOperations.add(money,valueSelect, buttonAdd);

        layoutAccuntValue.add(paragraphAccountValue);


        add(layoutAccuntValue, layoutAccountOperations);
    }


}
