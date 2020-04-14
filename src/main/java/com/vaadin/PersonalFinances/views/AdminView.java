package com.vaadin.PersonalFinances.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;

@Route(value = "admin", layout = MainView.class)
@PageTitle("ASDF")
public class AdminView extends Div {

    public AdminView() {

        VerticalLayout layoutVertical = new VerticalLayout();
        HorizontalLayout layoutAccuntValue = new HorizontalLayout();
        Label label = new Label("asd");
        H1 tekst = new H1("sample TEkst");
        H1 tekst1 = new H1("sample TEkst");

        layoutAccuntValue.add(tekst);
        layoutAccuntValue.setSpacing(true);

        Image image = new Image("https://dummyimage.com/600x400/000/fff", "DummyImage");
        layoutVertical.add(layoutAccuntValue, image);
        layoutAccuntValue.setAlignSelf(FlexComponent.Alignment.END, tekst);
        layoutVertical.setAlignSelf(FlexComponent.Alignment.STRETCH, image);


        add(layoutVertical);
    }



}
