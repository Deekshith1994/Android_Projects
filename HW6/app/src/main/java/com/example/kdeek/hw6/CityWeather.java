package com.example.kdeek.hw6;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CityWeather extends AppCompatActivity implements WeatherAsyncTask.getWeather{
    DatabaseManager db;
    ProgressDialog pd;
    ArrayList<Weather> wList = new ArrayList<Weather>();
    Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        loc = (Location) getIntent().getExtras().get("Location");
        db = new DatabaseManager(this);
       // new WeatherAsyncTask(this).execute("http://api.openweathermap.org/data/2.5/forecast?q="+loc.city+","+loc.country+"&mode=json&"+MainActivity.KEY_TAG);


    }

    @Override
    public void progressLoading() {
        pd = new ProgressDialog(this);
        pd.setMessage("Loading Image");
        pd.setCancelable(false);
        pd.setMax(100);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopts, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sC :
                Toast.makeText(this,"save"+loc.city,Toast.LENGTH_SHORT).show();

                db.saveNote(new Note(0,0,loc.city,loc.country,""));
                List<Note> notes = db.getAllNote();

                Log.d("demoDB", notes.toString());
            case R.id.set :


        }
        return super.onOptionsItemSelected(item);



    }

    @Override
    public void stopProgress() {
        pd.dismiss();

    }

    @Override
    public void setWeatherL(ArrayList<Weather> weatherL) {
        wList = weatherL;
        Log.d("demo1", wList.toString());
    }
}
