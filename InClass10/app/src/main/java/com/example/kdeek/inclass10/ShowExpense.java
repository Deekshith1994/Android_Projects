package com.example.kdeek.inclass10;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowExpense extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("Expenses");
    Expense e;
    String id;
    Button close;

    TextView nameView,catView,amtView,dateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);

        nameView = (TextView) findViewById(R.id.nameView);
        dateView = (TextView) findViewById(R.id.dateView);
        catView = (TextView) findViewById(R.id.catView);
        amtView = (TextView) findViewById(R.id.amtView);
        close = (Button) findViewById(R.id.buttonClose);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                setResult(Activity.RESULT_OK,in);
                finish();
            }
        });


        if(getIntent().getExtras() !=null){
            id = getIntent().getStringExtra("Expense");
            Log.d("Sdemo",id);
        }


        //SysmyRef.child(EmailPasswordActivity.curUser).child(id);



    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Log.d("Sdemo123",postSnapshot.toString());
                    if(postSnapshot.getKey().equals(EmailPasswordActivity.curUser)){
                        Log.d("Sdemo1234",postSnapshot.toString());

                        for (DataSnapshot expenses: postSnapshot.getChildren()){
                            Log.d("Sdemo1235",expenses.toString());
                            if(expenses.getKey().equals(id)) {
                                Log.d("Sdemo12356",expenses.toString());
                                e = expenses.getValue(Expense.class);
                            }
                        }
                        break;
                    }
                }
                Resources res = getResources();
                String[] cats = res.getStringArray(R.array.cat);


                nameView.setText(e.getExpenseName()+"");
                dateView.setText(e.getDate()+"");
                amtView.setText("$ "+e.getAmount());
                catView.setText(cats[e.getCategory()]+"");

            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
