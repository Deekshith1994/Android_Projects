package com.example.saisr.trivia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetTriviaQuestions.getQuestionsActivity {
    Button start_but, exit_but;
    ArrayList<Question> qList = new ArrayList<>();
    ProgressDialog pd;
    ImageView triviaImage;
    int resultCode ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_but = (Button) findViewById(R.id.start_but);
        exit_but = (Button) findViewById(R.id.exit_but);

        new GetTriviaQuestions(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        start_but.setEnabled(false);
        exit_but.setEnabled(false);



        start_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TriviaActivity.class);
                i.putParcelableArrayListExtra("TRIVIA", qList);
                startActivityForResult(i, 1);

            }
        });

        exit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                //String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }
    }


    public void startTrivia(ArrayList<Question> quest) {
        pd.dismiss();
        triviaImage = (ImageView) findViewById(R.id.triviaView);
        qList = quest;
        Resources res = getResources();
        triviaImage.setImageDrawable(res.getDrawable(R.drawable.trivia));
        start_but.setEnabled(true);
        exit_but.setEnabled(true);
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
}
