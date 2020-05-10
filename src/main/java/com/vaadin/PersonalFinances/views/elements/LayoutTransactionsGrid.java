package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.UI_Controllers.UserInfo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LayoutTransactionsGrid {
    @Autowired
    private UI_Http_Service transactionService;
    private UserInfo userInfo;

    public LayoutTransactionsGrid(){
        this.transactionService = new UI_Http_Service();
        this.userInfo = new UserInfo();
    }
    public Grid getGrid(){



        List<Transaction> transactionList =  getTransactionList();

        Grid<Transaction> grid = new Grid<>();

        grid.setItems(transactionList);
        grid.addColumn(Transaction::getTitle).setHeader("Title");
        grid.addColumn(Transaction::getCategory).setHeader("Category");
        grid.addColumn(Transaction::getType).setHeader("Type");
        grid.addColumn(Transaction::getAmountOfMoney).setHeader("Value");
        grid.addColumn(Transaction::getDate).setHeader("Date");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.setSizeFull();

        grid.addItemClickListener(event ->{
            System.out.println(event.getItem().getTitle());

        });
        return grid;
    }
    private List<Transaction> getTransactionList(){
        try{
            return transactionService.getWalletTransactions(userInfo.getWalletId());
        }catch (Exception e){

            return new ArrayList<Transaction>();
        }
    }
}
