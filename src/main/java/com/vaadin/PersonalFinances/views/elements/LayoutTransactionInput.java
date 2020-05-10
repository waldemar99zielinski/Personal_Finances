package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import com.vaadin.PersonalFinances.API.models.TransactionIncomeCategories;
import com.vaadin.PersonalFinances.API.models.TransactionType;
import com.vaadin.PersonalFinances.UI_Controllers.UI_WalletController;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LayoutTransactionInput {
    //Transaction variables
    String transactionTitle;
    String transactionCategory;
    TransactionType transactionType;
    BigDecimal transactionPrice;
    TransactionExpenseCategories transactionCategories;
    //Layout components
    TextField textField_titleInput;
    BigDecimalField bigDecimalField_priceInput;
    Select<TransactionType> select_transactionType;
    Select<String> select_categoryInput;
    HorizontalLayout layout_buttons;
    UI_WalletController walletController;

    public LayoutTransactionInput(){
        this.transactionCategories = new TransactionExpenseCategories();
        textField_titleInput = new TextField();
        bigDecimalField_priceInput = new BigDecimalField();
        select_categoryInput = new Select<>();
        select_transactionType = new Select<>();
        layout_buttons = new HorizontalLayout();
        walletController = new UI_WalletController();

    }
    public VerticalLayout mainLayout(){
        VerticalLayout main = new VerticalLayout();



        textField_titleInput.setLabel("Title");
        textField_titleInput.setWidth("50%");


        bigDecimalField_priceInput.setLabel("Price");
        bigDecimalField_priceInput.setWidth("50%");
        bigDecimalField_priceInput.setSuffixComponent(new Span("$"));

        select_transactionType.setItems(TransactionType.values());
        select_transactionType.setLabel("Type");
        select_transactionType.setValue(TransactionType.EXPENSE);
        select_transactionType.setWidth("50%");
        select_transactionType.setEmptySelectionAllowed(false);


        //select_categoryInput.setItems(transactionCategories.getCategories());
        populateCategories();
        select_categoryInput.setValue("Food");
        select_categoryInput.setLabel("Category");
        select_categoryInput.setWidth("50%");
        select_categoryInput.setEmptySelectionAllowed(false);
        //depending on transactin type populate category values
        select_transactionType.addValueChangeListener(event -> {

            populateCategories();

        });

        layout_buttons.setSizeFull();
        Button button_addTransaction = new Button("Add");
        Button button_clearInput = new Button("Clear");

        layout_buttons.setWidth("50%");
        button_addTransaction.setWidth("50%");
        button_clearInput.setWidth("50%");

        layout_buttons.add(button_addTransaction ,button_clearInput);
        button_addTransaction.addClickListener(event -> buttonAction_addTransaction());
        button_clearInput.addClickListener(event -> buttonAction_clearInputs());

        main.add(textField_titleInput, bigDecimalField_priceInput,select_transactionType, select_categoryInput, layout_buttons);

        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, select_categoryInput);
        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, select_transactionType);
        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, layout_buttons);
        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, textField_titleInput);
        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, bigDecimalField_priceInput);

        return main;
    }

    private void populateCategories(){
        if(select_transactionType.getValue() == TransactionType.INCOME){
            select_categoryInput.setItems(new TransactionIncomeCategories().getCategories());
            select_categoryInput.setValue("Active");
        }else{
            select_categoryInput.setItems(new TransactionExpenseCategories().getCategories());
            select_categoryInput.setValue("Food");
        }

    }


    private Dialog dialogResponse(){
        Dialog dialog = new Dialog();
       VerticalLayout layout = new VerticalLayout();
        Label priceErrorMessage = new Label("Price must be greater or equal 0");
        String titleErrorMessage = "Title can not be null";
        String succesfullMessage = "Transaction was added";
        if(!validatePriceInput()){
            layout.add(priceErrorMessage);
        }
        if(!validateTitleInput()){
            layout.add(titleErrorMessage);
        }
        if(validatePriceInput() && validateTitleInput()){
            layout.add(succesfullMessage);
        }



        dialog.add(layout);
       dialog.setWidth("500px");
       dialog.setHeight("200px");



       return  dialog;
    }
    private boolean validatePriceInput(){
        if(bigDecimalField_priceInput.isEmpty()){
            return false;
        }
        if( bigDecimalField_priceInput.getValue().compareTo(BigDecimal.ZERO)==1 || bigDecimalField_priceInput.getValue().compareTo(BigDecimal.ZERO)==0){
            return true;
        }

        return false;
    }
    //chceck if null
    private boolean validateTitleInput(){
        if(!textField_titleInput.isEmpty()){

            return true;
        }else{

            return false;
        }
    }
    private boolean validAllInputs(){
        if(validateTitleInput() && validatePriceInput()){
            return true;
        }else{
            return false;
        }
    }
    private void buttonAction_addTransaction(){
        if(validAllInputs()) {
           assignInputValuse();
           Transaction transactionToPost = createTransactionObject();
           walletController.createTransaction(transactionToPost);

        }
        dialogResponse().open();
    }
    private void assignInputValuse(){
        this.transactionPrice = bigDecimalField_priceInput.getValue();
        this.transactionCategory = select_categoryInput.getValue();
        this.transactionTitle = textField_titleInput.getValue();
        this.transactionType = select_transactionType.getValue();
    }
    private void buttonAction_clearInputs(){
        this.textField_titleInput.clear();
        this.bigDecimalField_priceInput.clear();

    }
    private Transaction createTransactionObject(){
        Transaction transaction = new Transaction();

        transaction.setAmountOfMoney(this.transactionPrice);
        transaction.setCategory(this.transactionCategory);
        transaction.setTitle(this.transactionTitle);


        transaction.setType(transactionType.toString());
        transaction.setDate(new Date());

        return transaction;
    }
}
