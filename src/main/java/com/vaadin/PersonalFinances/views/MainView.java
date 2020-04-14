package com.vaadin.PersonalFinances.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLink;

import java.util.HashMap;
import java.util.Map;



public class MainView extends AppLayout implements BeforeEnterObserver {

    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

    public MainView() {
        addMenuTab("Wallet", WalletView.class, new Icon(VaadinIcon.WALLET));
        addMenuTab("Admin", AdminView.class, new Icon(VaadinIcon.COG));
        addMenuTab("Dashboard", DashboardView.class,new Icon(VaadinIcon.COG));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        Image imageCoins = new Image("https://media0.giphy.com/media/NsAXBSpx0MJ6IBDCPY/source.gif", "coins");
        imageCoins.setHeight("44px");

        addToDrawer(tabs);
        addToNavbar(new Label("personal finances"), imageCoins);


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


