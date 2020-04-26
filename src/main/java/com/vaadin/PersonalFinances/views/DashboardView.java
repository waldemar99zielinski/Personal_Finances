package com.vaadin.PersonalFinances.views;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.time.LocalDate;

@Route(value = "dashboard", layout = MainView.class)
@CssImport("./styles/style.css")
public class DashboardView extends VerticalLayout {
    public DashboardView() {





        //add(h);
       // add(label);
        Label firstLabel = new Label("First content component aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Label secondLabel = new Label("Second content component");
        secondLabel.addClassName("text");
        Label thirdLabel = new Label("Third content component");
        Label forthLabel = new Label("Forth content component");

        HorizontalLayout l1 = new HorizontalLayout();
        VerticalLayout c1 = new VerticalLayout();

        c1.addClassName("a");
        VerticalLayout c2 = new VerticalLayout();
        c2.addClassName("a");
        //
        HorizontalLayout l2 = new HorizontalLayout();
       c1.add(firstLabel);

       c1.setSizeFull();

       c2.add(secondLabel);
       c2.setSizeFull();

        l1.add( c1, c2);

        l1.setSizeFull();
        l2.add(thirdLabel,forthLabel);
        l2.setSizeFull();
        SplitLayout layout = new SplitLayout(
                new Label("First content component"),
                new Label("Second content component"));
        setHeightFull();
        setWidthFull();

     /* setPrimaryStyle("maxWidth", "50%");
        setPrimaryStyle("minWidth", "50%");
      addToPrimary(firstLabel);

      addToSecondary(secondLabel);

      */
      add(l1,l2);
    }
}
