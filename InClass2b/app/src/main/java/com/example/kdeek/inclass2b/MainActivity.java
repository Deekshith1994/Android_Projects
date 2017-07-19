package com.example.kdeek.inclass2b;

import android.graphics.RadialGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText l1,l2,a;
    Button cal;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        l1 = (EditText) findViewById(R.id.editText2);
        l2 = (EditText) findViewById(R.id.editText4);
        a = (EditText) findViewById(R.id.editText6);
        cal = (Button) findViewById(R.id.calculate);
        rg = (RadioGroup) findViewById(R.id.AreaRG);



        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    float length1 = Float.valueOf(l1.getText().toString());
                    float length2 = Float.valueOf(l2.getText().toString());
                    float area;
                    int checkedID = rg.getCheckedRadioButtonId();

                    if (checkedID == R.id.triangle) {

                        area = (float) (0.5 * length1 * length2);
                        a.setText(area + "");

                    } else if (checkedID == R.id.square) {
                        area = (float) length1 * length1;
                        l2.setText("");
                        a.setText(area + "");

                    } else if (checkedID == R.id.rectangle) {
                        area = (float) length1 * length2;
                        a.setText(area + "");
                    } else if (checkedID == R.id.circle) {
                        area = (float) 3.14 * length1 * length1;
                        l2.setText("");
                        a.setText(area + "");

                    } else if (checkedID == R.id.clearAll) {
                        l1.setText("");
                        l2.setText("");
                        a.setText("");

                    }
                }catch(Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter the length/s", Toast.LENGTH_SHORT);
                    toast.show();

                }


            }
        });




    }

}