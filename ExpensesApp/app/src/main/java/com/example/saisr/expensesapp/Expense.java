package com.example.saisr.expensesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saisr on 9/14/2016.
 */
public class Expense implements Parcelable {
    private String expenseName, category;
    private int amount;
    int day, month, year;
    private String receipt;

    public Expense(String expenseName, int day,int month,int year, int amount, String category, String receipt) {
        this.expenseName = expenseName;
        // this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.category = category;
        this.receipt = receipt;
    }


    protected Expense(Parcel in) {
        expenseName = in.readString();
        category = in.readString();
        amount = in.readInt();
        day = in.readInt();
        month = in.readInt();
        year = in.readInt();
        receipt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expenseName);
        dest.writeString(category);
        dest.writeInt(amount);
        dest.writeInt(day);
        dest.writeInt(month);
        dest.writeInt(year);
        dest.writeString(receipt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };
}
