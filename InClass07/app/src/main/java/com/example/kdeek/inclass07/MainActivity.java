package com.example.kdeek.inclass07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GetData.gData{

    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Item> itemListget = new ArrayList<>();
    EditText text;
    ProgressDialog pd;
    ListView lv;
    Button get,clear;
    public  static final String NEWS = "news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = (Button) findViewById(R.id.button);
        clear = (Button) findViewById(R.id.button2);
        text = (EditText)  findViewById(R.id.editText);
        new GetData(MainActivity.this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/json");

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                itemListget=new ArrayList<Item>();
//                for(int i=0;i<itemList.size();i++)
//                {
//
//
//                    if(itemList.get(i).getTittle().toLowerCase().trim().contains(text.getText().toString().toLowerCase().trim())) {
//
//                        itemListget.add(itemList.get(i));
//
//                    }
//
//                }
//                itemList.removeAll(itemListget);
//                itemListget.addAll(itemListget.size(),itemList);
//                ListAdapter adapter=new ListAdapter(MainActivity.this,R.layout.itemlayout,itemListget,text.getText().toString());
//                lv.setAdapter(adapter);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
//                ListAdapter finalAdapter=new ListAdapter(MainActivity.this, R.layout.itemlayout,itemList,"");
//                lv.setAdapter(finalAdapter);

            }
        });



    }

    @Override
    public void setData(ArrayList<Item> itemList) {
        this.itemList = itemList;
        Log.d("Test",this.itemList.toString());

        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item lhs, Item rhs) {
                DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
                try {
                    Date startDate = df.parse(lhs.getPublish());
                    Date startDater = df.parse(rhs.getPublish());
                    return startDate.compareTo(startDater);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
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
    public void stopLoading() {
        pd.dismiss();
        ListView lv = (ListView) findViewById(R.id.listView);
        ListAdapter la = new ListAdapter(this,R.layout.itemlayout,itemList);
        la.setNotifyOnChange(true);
        lv.setAdapter(la);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this, Desc.class);
                in.putExtra("TRIVIA", itemList.get(i));
                startActivity(in);
            }
        });




    }
}
