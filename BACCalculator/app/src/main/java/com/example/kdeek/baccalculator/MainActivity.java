package com.example.kdeek.baccalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public float weight;
    public boolean gender_temp, gender;
    EditText wt;
    SeekBar sb;
    RadioGroup rg;
    Switch sw;
    float bac;
    int alcohol;
    float alcoholPercentage;
    TextView bacOut,statusOut;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wt = (EditText) findViewById(R.id.weight);
        rg = (RadioGroup) findViewById(R.id.radioGrp);
        sb = (SeekBar) findViewById(R.id.seekBar);
        sw = (Switch) findViewById(R.id.switch1);
        statusOut = (TextView) findViewById(R.id.statusOut);
        bacOut = (TextView) findViewById(R.id.bacOut);


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                alcoholPercentage = i;
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setText(i+"%");
            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               gender_temp = b;
            }
        });


    }

    public void saveButtonClick(View v){
        try{
            weight = Float.parseFloat(wt.getText().toString()) ;

            gender = gender_temp;
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"“Enter the weight in lbs", Toast.LENGTH_SHORT).show();
        }

    }

    public void addDrinkButtonClick(View v){

        int oz;
        float gc;

        if(sw.isChecked()) gc = (float) 0.68;
        else gc = (float) 0.55;



        if(rg.getCheckedRadioButtonId() == R.id.oz1) oz = 1;
        else if (rg.getCheckedRadioButtonId() == R.id.oz5) oz = 5;
        else oz = 12;


        alcohol += (oz*alcoholPercentage)/100;

        bac = (float)(alcohol * 6.24)/(weight * gc);

        bacOut.setText("BAC Output : "+ bac);

        if (bac < 0.08) status = "You're Safe";
        else if(bac <0.20) status = "Be Careful...";
        else status = "Over the Limit";

        statusOut.setText(status);

        if (bac < 0.08) statusOut.setBackgroundColor(Color.GREEN);
        else if(bac <0.20) statusOut.setBackgroundColor(Color.YELLOW);
        else statusOut.setBackgroundColor(Color.RED);
    }



    public void resetButtonClick(View v){


    }

}
