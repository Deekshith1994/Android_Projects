package com.example.saisr.expensesapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import java.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddExpense extends AppCompatActivity {
    static Handler handler;
    EditText addName, amount;
    DatePicker dp;
    Spinner selectCategory;
    Button addE;
    String expName, category;
    EditText dateView;
    int amnt, day, month,year;
    Uri uri;
    static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("demo","YO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Log.d("demo","YO");
        addName = (EditText)findViewById(R.id.addName);
        amount = (EditText) findViewById(R.id.addAmnt);
        dateView = (EditText) findViewById(R.id.dateView) ;
        addE = (Button) findViewById(R.id.addE);

        Log.d("demo","YO 1");


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what == 100) dateView.setText(message.getData().getString("DATE"));
                return false;
            }
        });

        Log.d("demo","Great");

    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            date="";
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date=(month+1)+"/"+day+"/"+year;
            Log.d("demo",date);
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("DATE",date);
            msg.what = 100;
            msg.setData(data);
            handler.sendMessage(msg);

        }
    }




    public void add(View v){
        addName = (EditText) findViewById(R.id.addName);
        amount = (EditText) findViewById(R.id.addAmnt);



        expName = addName.getText().toString();
        amnt = Integer.parseInt(amount.getText().toString());
        day = dp.getDayOfMonth();
        month = dp.getMonth() + 1;
        year = dp.getYear();
        category = selectCategory.getSelectedItem().toString();


        if(expName.equals(null) || amnt == 0 || day == 0 ){
            Toast.makeText(AddExpense.this, "Looks like some field is missing", Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED);
        } else{
            Expense e = new Expense(expName,day,month, year, amnt, category,uri.toString());
            Intent i = new Intent();
            i.putExtra(MainActivity.ExpenseKey,e);
            setResult(RESULT_OK,i);

        }
        finish();
    }
}
