package com.vaadin.PersonalFinances.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
public class WalletView extends Div {
    public WalletView() {

        HorizontalLayout layout = new HorizontalLayout();
        Label labelAccountValue = new Label("current account balance:");
       
        layout.add(labelAccountValue);
        add(layout);
    }
}
