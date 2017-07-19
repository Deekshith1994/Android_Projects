package com.example.saisr.expensesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addE, editE, delE, showE, finish;
    List<String> cuurentE = new ArrayList<>();
    final static int ReqCode = 100;
    final static String ExpenseKey = "Expense";
    //final static int AmountKey = 101;
    //final static String CategoryKey = "CK";
    //final static int MonthKey = 102;
    //final static int DayKey = 103;
    //final static int YearKey = 104;

    Expense e;
    ArrayList<Expense> eList = new ArrayList<>();
    String name, catogery, date, image;
    int amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addE = (Button) findViewById(R.id.addExpenses);
        editE = (Button) findViewById(R.id.editExpenses);
        delE = (Button) findViewById(R.id.deleteExpense);
        showE = (Button) findViewById(R.id.showExpense);
        finish = (Button) findViewById(R.id.finish);
        // e = new Expense(name, date, amount, catogery, image);

    }

    public void addExpense(View v) {
        Log.d("demo","YO");
        Intent i = new Intent(MainActivity.this, AddExpense.class);
        startActivityForResult(i, ReqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "Looks like some field is missing", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_OK) {
                eList.add((Expense) getIntent().getExtras().getParcelable(ExpenseKey));
            }
        }

    }

}