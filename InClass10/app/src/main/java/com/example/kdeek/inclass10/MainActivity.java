package com.example.kdeek.inclass10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myRef.setValue("Hello, World!");
        writeNewUser("1","Deekshith","dkallem@uncc.edu");

       // Intent i = new Intent(MainActivity.this,EmailPasswordActivity.class);
      //  startActivity(i);


    }


    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User post = dataSnapshot.getValue(User.class);
                Log.d("demo", "Value is: " + post.toString());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("demo", "Failed to read value.", error.toException());
            }
        });
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        myRef.child("users").child(userId).setValue(user);
    }
}
