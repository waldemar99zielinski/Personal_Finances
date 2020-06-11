package com.vaadin.PersonalFinances.API.ErrorHandling;

import com.vaadin.PersonalFinances.API.models.Statistics;

public class StatisticsError {
    public StatisticsError(){

    }
    public Statistics getStatiticsErrorBody(){
        Statistics statistics = new Statistics();

        return statistics;
    }
}
