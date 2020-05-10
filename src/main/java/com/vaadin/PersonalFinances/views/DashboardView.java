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
import com.vaadin.PersonalFinances.views.elements.LayoutTransactionsGrid;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Route(value = "dashboard", layout = MainView.class)
@CssImport("./styles/style.css")
public class DashboardView extends Div {
    private UI_WalletController walletController;

    public DashboardView() {
        walletController = new UI_WalletController();

        Statistics s = walletController.getWalletStatistics();

        LayoutTransactionsGrid layoutTransactionsGrid = new LayoutTransactionsGrid();
        VerticalLayout h1 = new VerticalLayout();
        h1.add(layoutTransactionsGrid.getGrid(), new Label("asdf"));

        // Our Apex chart
        ApexCharts apexChart = new ApexCharts();


        Integer[] data = Arrays.stream(s.getNumberByCategory())
                                        .boxed()
                                        .toArray(Integer[]::new);
        // Series
        Series<Integer> series = new Series<Integer>();
        series.setData(data);

        series.setName("a");

        // Chart
        Chart chart = new Chart();

        chart.setType(Type.histogram);

        chart.setHeight("350");
        TitleSubtitle titleSubtilte = new TitleSubtitle();
        titleSubtilte.setText("Product Trends by Month");
        titleSubtilte.setAlign(Align.center);


        // Include them all
        apexChart.setSeries(series);
        apexChart.setChart(chart);
        apexChart.setTitle(titleSubtilte);
        apexChart.setLabels(new TransactionExpenseCategories().getCategoriesArray());


        // Render them and include into the content


        add(apexChart);



    }
}
