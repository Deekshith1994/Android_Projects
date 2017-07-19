package com.example.saisr.expensesapp;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EditExpense extends AppCompatActivity {
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);


    }
    public void selectClick(View v){

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void vieDia(){

        final CharSequence[] items = {"","","","","","","","","",""};
        for(Expense e:elist){

            //items[i] = passwords.get(i).toString();
        }
       // Log.d("demo",passwords.toArray().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an Expense")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try{
                            passDisp.setText(items[i]+"");
                        }catch (Exception e) {
                            passDisp.setText("Select a Password");
                        }
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
