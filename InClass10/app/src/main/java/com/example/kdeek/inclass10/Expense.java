package com.example.kdeek.inclass10;

/**
 * Created by kdeek on 11/7/2016.
 */
public class Expense {


    public String expenseName, date;
    public int category;
    public int amount;
    public String id;

    public Expense(String expenseName, String date, int category, int amount) {
        this.expenseName = expenseName;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }
    public Expense(){

    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
