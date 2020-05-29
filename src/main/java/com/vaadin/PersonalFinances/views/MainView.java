package com.vaadin.PersonalFinances.views;

import com.vaadin.PersonalFinances.UI_Controllers.CheckCredentials;
import com.vaadin.PersonalFinances.views.elements.LayoutLogin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLink;


import java.util.HashMap;
import java.util.Map;



public class MainView extends AppLayout implements BeforeEnterObserver {

    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();
    Button login ;
    public MainView() {

        addMenuTab("Wallet", WalletView.class, new Icon(VaadinIcon.WALLET));
        addMenuTab("Add transaction", AddTransactionView.class, new Icon(VaadinIcon.PLUS));
        addMenuTab("List", TransactionListView.class,new Icon(VaadinIcon.LINES));
        addMenuTab("Stats", StatsView.class,new Icon(VaadinIcon.BAR_CHART));
        //addMenuTab("Calendar", CalendarView.class,new Icon(VaadinIcon.CALENDAR));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        addToDrawer(tabs);

        login = new Button("Login");

        login.addClickListener(event -> new LayoutLogin().dialogAction());

        addToNavbar(new DrawerToggle(),new Label("personal finances"), login);
        login.getStyle().set("margin-left", "auto");

        new CheckCredentials();
    }

    private void addMenuTab(String label, Class<? extends Component> target, Icon icon) {
        Tab tab = new Tab(icon ,new RouterLink(label, target));
        navigationTargetToTab.put(target, tab);
        tabs.add(tab);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        tabs.setSelectedTab(navigationTargetToTab.get(event.getNavigationTarget()));
    }

}


