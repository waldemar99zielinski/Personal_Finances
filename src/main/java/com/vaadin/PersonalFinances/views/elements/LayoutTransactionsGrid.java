package com.vaadin.PersonalFinances.views.elements;

import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.UI_Controllers.UserInfo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.provider.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LayoutTransactionsGrid {
    @Autowired
    private UI_Http_Service transactionService;
    private UserInfo userInfo;
    private List<Transaction> transactionsList;

    public LayoutTransactionsGrid(){
        this.transactionService = new UI_Http_Service();
        this.userInfo = new UserInfo();
        populateTransacionGrid();
    }

    public Grid getGrid(){





        Grid<Transaction> grid = new Grid<>();
        grid.setItems(transactionsList);
        //columns
        grid.addColumn(Transaction::getTitle).setHeader("Title");
        grid.addColumn(Transaction::getCategory).setHeader("Category");
        grid.addColumn(Transaction::getType).setHeader("Type");
        grid.addColumn(Transaction::getAmountOfMoney).setHeader("Value");
        grid.addColumn(Transaction::getCurrency).setHeader("Currency");
        Grid.Column<Transaction> date = grid.addColumn(Transaction::getDate).setHeader("Date");
        //grid.addColumn(date);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        //sort by date
        GridSortOrder<Transaction> dateOrder = new GridSortOrder<>(date, SortDirection.DESCENDING);
        grid.sort(Arrays.asList(dateOrder));

        grid.setSizeFull();

        return grid;
    }
    private void populateTransacionGrid(){
        try{
            //System.out.println("LayoutTransactionGrid: walletId"+ userInfo.getWalletId());
            transactionsList = transactionService.getWalletTransactions();



        }catch (Exception e){
            System.out.println("LayoutTransactionGrid: populateTransactionGrid\n");
            System.out.println(e.getMessage());

        }
    }
}
