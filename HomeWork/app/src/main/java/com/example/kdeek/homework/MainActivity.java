package com.example.kdeek.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"ON CREATE", Toast.LENGTH_SHORT).show();
        Log.d("test","ON CREATE");
        button = (Button) findViewById(R.id.button);
       // back = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.new_layout);
            }
        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setContentView(R.layout.activity_main);
//            }
//        });

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Toast.makeText(MainActivity.this,"ON START", Toast.LENGTH_SHORT).show();
        Log.d("test","ON START");
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Toast.makeText(MainActivity.this,"ON RESUME", Toast.LENGTH_SHORT).show();
        Log.d("test","ON RESUME");
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Toast.makeText(MainActivity.this,"ON PAUSE", Toast.LENGTH_SHORT).show();
        Log.d("test","ON PAUSE");
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Toast.makeText(MainActivity.this,"ON RESTART", Toast.LENGTH_SHORT).show();
        Log.d("test","ON RESTART");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Toast.makeText(MainActivity.this,"ON STOP", Toast.LENGTH_SHORT).show();
        Log.d("test","ON STOP");
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(MainActivity.this,"ON DESTROY", Toast.LENGTH_SHORT).show();
        Log.d("test","ON DESTROY");
    }
}
