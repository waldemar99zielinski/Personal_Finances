package com.vaadin.PersonalFinances.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.time.LocalDate;

@Route(value = "dashboard", layout = MainView.class)
public class DashboardView extends Div {
    public DashboardView() {

        add(new Span("Dashboard view content"));
        FullCalendar calendar = FullCalendarBuilder.create().build();
        Entry entry = new Entry();
        entry.setTitle("Some event");
        entry.setStart(LocalDate.now().withDayOfMonth(3).atTime(10, 0), calendar.getTimezone());
        entry.setEnd(entry.getStart().plusHours(2), calendar.getTimezone());
        entry.setColor("#ff3333");

        //calendar.addEntry(entry);

        add(calendar);
        //container.setFlexGrow(1, calendar);;
    }
}
