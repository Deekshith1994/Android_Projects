package com.example.kdeek.inclass11;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VisitPlaces extends AppCompatActivity implements  GetDataAsync.getActivity{
    public static ArrayList<DataItem> dataItemArrayList = new ArrayList<>();

    Button logout_but,fav_cities_but,pv_but,go_but;
    EditText cityEntered;
    ProgressDialog pd;
    public static String cID = "EDCUTFCVGZMBZARVBWSA3TY1AEA3HERMSGJYB4NVF2Y311LW";
    public static String cSec = "PD0QEQBTPL3MKUSUG2ECVABXZYGAARH35AIPXPVB0KYVZYKT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_places);

        logout_but = (Button) findViewById(R.id.but_logout);
        fav_cities_but = (Button) findViewById(R.id.fav_cities_button);
        pv_but = (Button) findViewById(R.id.pv_button);
        go_but = (Button) findViewById(R.id.go_but);
        cityEntered = (EditText) findViewById(R.id.et_cityEntered) ;

        go_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date cDate = new Date();
                String fDate = new SimpleDateFormat("yyyyMMdd").format(cDate);
                new GetDataAsync(VisitPlaces.this).execute("https://api.foursquare.com/v2/venues/search?client_id=EDCUTFCVGZMBZARVBWSA3TY1AEA3HERMSGJYB4NVF2Y311LW&client_secret=PD0QEQBTPL3MKUSUG2ECVABXZYGAARH35AIPXPVB0KYVZYKT&v="+fDate+"&near="+cityEntered.getText().toString());


            }
        });

        logout_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();

            }
        });


        fav_cities_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pv_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void progressLoading() {
        pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.setMax(100);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    public void setData(ArrayList<DataItem> list) {
        pd.dismiss();
        dataItemArrayList = list;

        //Toast.makeText(this,dataItemArrayList.toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(VisitPlaces.this,Venues.class);
        startActivityForResult(intent,9000);



    }
}
