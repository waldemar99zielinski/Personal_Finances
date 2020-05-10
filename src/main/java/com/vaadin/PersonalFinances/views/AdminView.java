package com.vaadin.PersonalFinances.views;

import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.views.elements.LayoutTransactionInput;
import com.vaadin.PersonalFinances.views.elements.LayoutTransactionsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "admin", layout = MainView.class)
@PageTitle("ASDF")
@CssImport("./styles/style.css")
public class AdminView extends Div {

    @Autowired
    private UI_Http_Service transactionService;

    private LayoutTransactionInput layoutTransactionInput;

    public AdminView() {



        layoutTransactionInput = new LayoutTransactionInput();
        VerticalLayout main = layoutTransactionInput.mainLayout();

        LayoutTransactionsGrid grid = new LayoutTransactionsGrid();

        Tab tab1 = new Tab("Expenses");


        Tab tab2 = new Tab("Tab two");
        Div page2 = new Div();
        page2.setText("Page#2");
        page2.setVisible(false);

        Tab tab3 = new Tab("Tab three");
        Div page3 = new Div();
        page3.setText("Page#3");
        page3.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, main);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        Div pages = new Div(main, page2, page3);
        Set<Component> pagesShown = Stream.of(main)
                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
            //System.out.println("select");
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });

        add(tabs, pages);

    }



}
