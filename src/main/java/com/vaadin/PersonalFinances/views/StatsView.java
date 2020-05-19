package com.vaadin.PersonalFinances.views;




import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.Chart;
import com.github.appreciated.apexcharts.config.TitleSubtitle;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.Zoom;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import com.vaadin.PersonalFinances.API.models.TransactionIncomeCategories;
import com.vaadin.PersonalFinances.UI_Controllers.UI_WalletController;
import com.vaadin.PersonalFinances.views.elements.LayoutStats;
import com.vaadin.PersonalFinances.views.elements.LayoutTransactionsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "dashboard", layout = MainView.class)
@CssImport("./styles/style.css")
public class StatsView extends Div {
    private UI_WalletController walletController;

    public StatsView() {
        setSizeFull();


        Tab tab2 = new Tab("Expenses");
        Div page2 = new Div();
        page2.add(new LayoutStats().expenses());
        page2.setVisible(true);

        Tab tab3 = new Tab("Incomes");
        Div page3 = new Div();
        page3.add(new LayoutStats().incomes());
        page3.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();

        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Tabs tabs = new Tabs(tab2, tab3);
        Div pages = new Div( page2, page3);
        Set<Component> pagesShown = Stream.of(page2)
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
