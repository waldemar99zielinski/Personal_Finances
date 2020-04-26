package com.vaadin.PersonalFinances.views;


import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.Wallet;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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

    @Autowired
    private UI_Http_Service transactionService;


    public WalletView() {
        transactionService = new UI_Http_Service();


        List<Transaction> transactionList =  transactionService.getWalletTransactions("5ea408cafeb8764046ad7de2");
        Grid<Transaction> grid = new Grid<>();
        grid.setItems(transactionList);
        grid.addColumn(Transaction::getTitle).setHeader("Title");
        grid.addColumn(Transaction::getCategory).setHeader("Category");
        grid.addColumn(Transaction::getAmountOfMoney).setHeader("Value");
        grid.addColumn(Transaction::getDate).setHeader("Date");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        grid.asSingleSelect().addValueChangeListener(event ->{
            System.out.println(event.getValue().getTitle());

                });


        add(grid);



    }


}
