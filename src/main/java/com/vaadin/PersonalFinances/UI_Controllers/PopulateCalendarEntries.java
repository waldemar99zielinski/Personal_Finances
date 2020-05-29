package com.vaadin.PersonalFinances.UI_Controllers;

import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.stefan.fullcalendar.Entry;

import java.time.LocalDate;
import java.util.List;

public class PopulateCalendarEntries {
    @Autowired
    private UI_Http_Service transactionService;

    public PopulateCalendarEntries(){
        transactionService = new UI_Http_Service();

    }
    public Entry[] getEntries(){
        try {
            List<Transaction> transactionList = transactionService.getExpenseWalletTransactions();
            Entry[] entries = new Entry[transactionList.size()];

            // convert Transactions to Entries
            for(int i =0;i<transactionList.size(); i++){
                entries[i] = createEntry(transactionList.get(i));
            }

            return entries;
        }catch (Exception e){
            System.out.println("PopulateCalendarEntries: "+ e.getMessage());
            Entry[] entries= new Entry[1];
            entries[0] = new Entry();
            return entries;
        }

    }
    private Entry createEntry(Transaction transaction){
        Entry entry = new Entry();
        entry.setTitle(transaction.getTitle()+ " "+transaction.getAmountOfMoney().toString()+"$");
        entry.setStart(transaction.getDate().toInstant());
       //color depends on transaction type

        if(transaction.getType().equals(TransactionType.EXPENSE.toString())){
           //red
            entry.setColor("#ff3333");
       }else if(transaction.getType().equals(TransactionType.INCOME.toString())){
           //green
            entry.setColor("#1B9312");
       }

        return entry;
    }
}
