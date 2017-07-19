package com.example.kdeek.inclass2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText len1,len2;
    TextView area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        len1 = (EditText) findViewById(R.id.len1);
        len2 = (EditText) findViewById(R.id.len2);
        area = (TextView) findViewById(R.id.area);
    }




    public void squareArea(View v){
        try{
            float length1 = Float.valueOf(len1.getText().toString());
            float length2 = Float.valueOf(len2.getText().toString());
            float areaout = length1 * length1 ;
            len2.setText("");
            area.setText(areaout+"");

        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the length/s", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void circleArea(View v){
        try {
            float length1 = Float.valueOf(len1.getText().toString());
            float length2 = Float.valueOf(len2.getText().toString());
            float areaout = (float) (3.14 * length1 * length2);
            len2.setText("");
            area.setText(areaout + "");
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the length/s", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void triangleArea(View v){

        try {


            float length1 = Float.valueOf(len1.getText().toString());
            float length2 = Float.valueOf(len2.getText().toString());
            float areaout = (float) (0.5 * length1 * length2);
            area.setText(areaout + "");
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the length/s", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void rectanglrArea(View v){
        try {
            float length1 = Float.valueOf(len1.getText().toString());
            float length2 = Float.valueOf(len2.getText().toString());
            float areaout = length1 * length2;
            area.setText(areaout + "");
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the length/s", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void clearArea(View v){
        len1.setText("");
        len2.setText("");
        area.setText("");
    }
}
