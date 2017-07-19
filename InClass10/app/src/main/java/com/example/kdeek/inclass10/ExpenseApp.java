package com.example.kdeek.inclass10;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExpenseApp extends AppCompatActivity {

    TextView tv;
    ListView expList;
    ArrayList<Expense> eList = new ArrayList<>();
    ImageView add;
    Button SignOut;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("Expenses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eList.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_app);
        tv = (TextView)findViewById(R.id.textView);
        expList = (ListView) findViewById(R.id.eList123);
        SignOut = (Button) findViewById(R.id.signOut);

        add = (ImageView) findViewById(R.id.addExpense);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExpenseApp.this, AddExpense.class);
                startActivityForResult(i,102);
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eList.clear();
               Log.d("demo123",dataSnapshot.toString());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Log.d("demo123",postSnapshot.toString());
                    if(postSnapshot.getKey().equals(EmailPasswordActivity.curUser)){
                        for (DataSnapshot expenses: postSnapshot.getChildren()){
                            Log.d("demo1234",expenses.getValue().toString());
                            eList.add(expenses.getValue(Expense.class));
                        }
                    }
                }

               // Toast.makeText(ExpenseApp.this,dataSnapshot.toString(),Toast.LENGTH_SHORT).show();
                ExpenseListAdapter adapter = new ExpenseListAdapter(ExpenseApp.this, R.layout.row_expense_layout, eList);
                //ListView expList = (ListView) findViewById(R.id.eList);
                expList.setAdapter(adapter);
                adapter.setNotifyOnChange(true);

                int index = eList.size();
                if(index == 0)
                {
                    expList.setVisibility(View.INVISIBLE);
                }else{
                    expList.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                }

                expList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //getFragmentManager().beginTransaction().replace(R.id.layout_container, new ShowExpense(), "showExpense").addToBackStack(null).commit();
                        Intent in = new Intent(ExpenseApp.this, ShowExpense.class);
                        in.putExtra("Expense", eList.get(i).id);
                        startActivityForResult(in,103);
                    }
                });

                expList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        myRef.child(EmailPasswordActivity.curUser).child(eList.get(i).id).removeValue();
                        return true;
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
