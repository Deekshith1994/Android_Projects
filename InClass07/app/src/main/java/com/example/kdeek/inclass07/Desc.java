package com.example.kdeek.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Desc extends AppCompatActivity {
    Item item = null;

    TextView  title,date,desc;
    ImageView iV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        desc = (TextView) findViewById(R.id.desc);
        iV = (ImageView) findViewById(R.id.iViewL);

        if (getIntent().getExtras() != null) {
            item = getIntent().getExtras().getParcelable("TRIVIA");
            Log.d("demo123",item.getTittle()+"Hello");
        }


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //MM/DD/YYYY HH:MM AM/ PM
        try {
            Date startDate = df.parse(item.getLink());
            SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa", Locale.getDefault());
            date.setText(sd.format(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,item.getTittle(), Toast.LENGTH_SHORT);
        title.setText(item.getTittle().toString());
       // date.setText(item.getPublish());
        desc.setText(item.getDesc());
        String url= "";

        Picasso.with(this).load(item.getlURL()).into(iV);


    }
}
