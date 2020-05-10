package com.vaadin.PersonalFinances.views;

import com.vaadin.PersonalFinances.views.elements.LayoutTransactionsGrid;
import com.vaadin.PersonalFinances.views.elements.LayoutWalletOverview;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "transactionList", layout = MainView.class)
public class TransactionListView extends Div{

    private LayoutTransactionsGrid layoutTransactionsGrid;



    public TransactionListView() {

        layoutTransactionsGrid = new LayoutTransactionsGrid();


        setSizeFull();

        add(layoutTransactionsGrid.getGrid());

    }



}
