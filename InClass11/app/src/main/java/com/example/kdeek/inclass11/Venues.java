package com.example.kdeek.inclass11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Venues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);


        RecyclerView msgs = (RecyclerView) findViewById(R.id.fromlinkrV);

        recyclerAdapter adapter1 = new recyclerAdapter(Venues.this, VisitPlaces.dataItemArrayList, new recyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {

                    }
                });
                msgs.setAdapter(adapter1);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Venues.this, LinearLayoutManager.VERTICAL, false);
                msgs.setLayoutManager(layoutManager);

    }
}
