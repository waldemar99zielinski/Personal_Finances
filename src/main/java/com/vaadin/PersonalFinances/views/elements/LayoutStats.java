package com.vaadin.PersonalFinances.views.elements;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.Chart;
import com.github.appreciated.apexcharts.config.TitleSubtitle;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.PersonalFinances.API.models.Statistics;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import com.vaadin.PersonalFinances.API.models.TransactionIncomeCategories;
import com.vaadin.PersonalFinances.UI_Controllers.UI_Http_Service;
import com.vaadin.PersonalFinances.UI_Controllers.UI_WalletController;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.math.BigDecimal;
import java.util.Arrays;

public class LayoutStats {
    UI_WalletController walletController;
    public LayoutStats(){
        walletController = new UI_WalletController();
    }
    public VerticalLayout expenses(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.add(chartExpenses("money"));
        layout.add(chartExpenses("numbers"));
        return layout;
    }
    public VerticalLayout incomes(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.add(chartIncomes("money"));
        layout.add(chartIncomes("numbers"));
        return layout;
    }
    ApexCharts chartExpenses(String option){
        Statistics s = walletController.getExpenseWalletStatistics();


        // Our Apex chart
        ApexCharts apexChart = new ApexCharts();

        if(option.equals("numbers")){
            Integer[] data = Arrays.stream(s.getNumberByCategory())
                    .boxed()
                    .toArray(Integer[]::new);
            // Series
            Series<Integer> series = new Series<Integer>();

            series.setData(data);

            series.setName("numbers");
            apexChart.setSeries(series);
        }else{
            BigDecimal[] data = s.getMoneyByCategory();
            // Series
            Series<BigDecimal> series = new Series<BigDecimal>();

            series.setData(data);

            series.setName("money");
            apexChart.setSeries(series);
        }


        // Chart
        Chart chart = new Chart();

        chart.setType(Type.histogram);

        chart.setHeight("200%");
        TitleSubtitle titleSubtilte = new TitleSubtitle();
        titleSubtilte.setText("Product Trends by Month");
        titleSubtilte.setAlign(Align.center);


        // Include them all

        apexChart.setChart(chart);
        apexChart.setTitle(titleSubtilte);
        apexChart.setLabels(new TransactionExpenseCategories().getCategoriesArray());


        // Render them and include into the content
        return apexChart;
    }
    ApexCharts chartIncomes(String option){
        Statistics s = walletController.getIncomeWalletStatistics();


        // Our Apex chart
        ApexCharts apexChart = new ApexCharts();

        if(option.equals("numbers")){
            Integer[] data = Arrays.stream(s.getNumberByCategory())
                    .boxed()
                    .toArray(Integer[]::new);
            // Series
            Series<Integer> series = new Series<Integer>();

            series.setData(data);

            series.setName("numbers");
            apexChart.setSeries(series);
        }else{
            BigDecimal[] data = s.getMoneyByCategory();
            // Series
            Series<BigDecimal> series = new Series<BigDecimal>();

            series.setData(data);

            series.setName("money");
            apexChart.setSeries(series);
        }


        // Chart
        Chart chart = new Chart();

        chart.setType(Type.histogram);

        chart.setHeight("200%");
        TitleSubtitle titleSubtilte = new TitleSubtitle();
        titleSubtilte.setText("Product Trends by Month");
        titleSubtilte.setAlign(Align.center);


        // Include them all

        apexChart.setChart(chart);
        apexChart.setTitle(titleSubtilte);
        apexChart.setLabels(new TransactionIncomeCategories().getCategoriesArray());


        // Render them and include into the content
        return apexChart;
    }
}
