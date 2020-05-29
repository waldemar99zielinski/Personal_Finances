package com.vaadin.PersonalFinances.views;

import com.vaadin.PersonalFinances.UI_Controllers.PopulateCalendarEntries;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.time.LocalDate;

@Route(value = "calendarView", layout = MainView.class)
public class CalendarView extends VerticalLayout {

    private PopulateCalendarEntries populateCalendarEntries;

    public CalendarView(){
        setSizeFull();
        /*
        populateCalendarEntries = new PopulateCalendarEntries();
        FullCalendar calendar = FullCalendarBuilder.create().build();


// Create a initial sample entry


        calendar.addEntries(populateCalendarEntries.getEntries());

        calendar.setSizeFull();
        add(calendar);

         */
    }
   //TODO: test Calendar pls
}


