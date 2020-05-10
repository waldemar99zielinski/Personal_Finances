package com.vaadin.PersonalFinances.views;


import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.views.elements.LayoutTransactionsGrid;
import com.vaadin.PersonalFinances.views.elements.LayoutWalletOverview;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Route(value = "", layout = MainView.class)
public class WalletView extends Div {

    private LayoutTransactionsGrid layoutTransactionsGrid;


    public WalletView() {


        setSizeFull();
        LayoutWalletOverview layoutWalletOverview = new LayoutWalletOverview();
        add(layoutWalletOverview.returnLayout());


    }


}
