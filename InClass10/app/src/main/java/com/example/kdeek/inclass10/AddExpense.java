package com.example.kdeek.inclass10;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddExpense extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("Expenses");



    EditText addName, amount;
    DatePicker dp;
    Spinner selectCategory;
    Button addE;
    int catPos = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("demo","YO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);



        addName = (EditText)findViewById(R.id.addName);
        amount = (EditText) findViewById(R.id.addAmnt);
        addE = (Button) findViewById(R.id.addE);
        selectCategory = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCategory.setAdapter(adapter);

        selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                catPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //User post = dataSnapshot.getValue(User.class);
                Log.d("demo","DATA CHANGED");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("demo", "Failed to read value.", error.toException());
            }
        });
    }

    private void writetoUser(String ExpenseName, int cat, int cost) {

        if(!ExpenseName.equals("")&&  cost != 0) {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());

            Expense user = new Expense(ExpenseName, formattedDate, catPos, cost);
            user.id = ExpenseName+((int)(Math.random()*100000));
            myRef.child(EmailPasswordActivity.curUser).child(user.id).setValue(user);

            Intent in = new Intent();
            setResult(Activity.RESULT_OK,in);
            finish();
        }
        else{
            Toast.makeText(AddExpense.this,"Enter Valid Data",Toast.LENGTH_SHORT).show();
        }

    }

    public void add(View v) {
        writetoUser(addName.getText().toString(),selectCategory.getId() , Integer.parseInt(amount.getText().toString()));
    }
}