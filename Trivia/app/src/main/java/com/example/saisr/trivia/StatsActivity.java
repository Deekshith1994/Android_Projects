package com.example.saisr.trivia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {
    //  int progress;
    int correct, questions;
    ProgressBar pb;
    TextView progress, tv2;
    Button quit, retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        if (getIntent().getExtras() != null) {
            correct = getIntent().getExtras().getInt("correct");
            questions = getIntent().getExtras().getInt("questions");
        }

        pb = (ProgressBar) findViewById(R.id.pBar);
        progress = (TextView) findViewById(R.id.progressView);
        tv2 = (TextView) findViewById(R.id.textView2);
        quit = (Button) findViewById(R.id.quit_but);
        retry = (Button) findViewById(R.id.tAgain);
        pb.setMax(questions);
        pb.setProgress(correct);
        progress.setText(((100 * correct) / questions) + "%");

        if (correct != questions) {
            tv2.setText("Try again and see if you can get\n" +
                    "all the correct answers!");
        } else {
            tv2.setText("Congrats");
        }

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(Activity.RESULT_CANCELED,i);
                finish();
            }
        });
    }
}
