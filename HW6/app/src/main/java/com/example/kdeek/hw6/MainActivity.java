package com.example.kdeek.hw6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    public static final String KEY_TAG = "appid=8e5b56d7e6390e7743bea9fc5abb0f9f";
    Button search;
    ListView lv;
    TextView tv;
    EditText city, country;

    String cityN,countryN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.submit);
        lv = (ListView) findViewById(R.id.favListView);
        tv = (TextView) findViewById(R.id.noFavorites);
        city = (EditText) findViewById(R.id.city);
        country = (EditText) findViewById(R.id.state);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityN = city.getText().toString();
                countryN = country.getText().toString();
                Location l = new Location(cityN, countryN);
                Intent i = new Intent(MainActivity.this, CityWeather.class);
                i.putExtra("Location",l);
                startActivity(i);

            }
        });


    }

}
