package com.vaadin.PersonalFinances.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.sql.Time;
import java.sql.Timestamp;

@Route(value = "admin", layout = MainView.class)
public class AdminView extends Div {
    int a = 5;
    public AdminView() {

        add(new Span("Admin view content" + a));
    }
}
