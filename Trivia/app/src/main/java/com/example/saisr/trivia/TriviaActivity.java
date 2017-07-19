package com.example.saisr.trivia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements GetQuestionImage.getImageActivity {
    ArrayList<Question> qList = new ArrayList<>();
    ImageView questImage;
    RadioGroup choice_rg;
    TextView questView, tLeft, qNoView;
    Button next_But, exit_But;
    int qID = -1;
    int id = 0;
    String question = "";
    String imageURL = "";
    int answer;
    int Oldanswer;
    ArrayList<String> opts = new ArrayList<>();
    ProgressDialog pd;
    String selectedOption = "";
    int questions = 0;
    int correct = 0;
    long startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        questions = -1;
        correct = 0;
        setContentView(R.layout.activity_trivia);
        startTime = (int) (System.currentTimeMillis() / 1000);

        if (getIntent().getExtras() != null) {
            qList = getIntent().getParcelableArrayListExtra("TRIVIA");
        }

        questView = (TextView) findViewById(R.id.questView);
        next_But = (Button) findViewById(R.id.next_Button);
        exit_But = (Button) findViewById(R.id.exit_button);
        choice_rg = (RadioGroup) findViewById(R.id.choice_rg);
        questImage = (ImageView) findViewById(R.id.questImage);
        tLeft = (TextView) findViewById(R.id.tLeft);
        qNoView = (TextView) findViewById(R.id.qNoView);
        Oldanswer = qList.get(0).answer;
        viewNextQuest();


        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                tLeft.setText("Time Left : " + millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                Intent i = new Intent(TriviaActivity.this, StatsActivity.class);
                i.putExtra("correct", correct);
                i.putExtra("questions", 16);
                startActivityForResult(i,4);
            }
        }.start();
        next_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNextQuest();

            }
        });
        exit_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TriviaActivity.this, StatsActivity.class);
                i.putExtra("correct", correct);
                i.putExtra("questions", 16);
                startActivityForResult(i,3);
            }
        });
        //Log.d("ArrayTrivia",qList.get(5).question.toString());
    }

    @Override
    public void setImage(Bitmap bm) {
        questImage.setImageBitmap(bm);
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
    public void stopLoading() {
        pd.dismiss();
    }

    public void viewNextQuest() {

        qID++;
        if (qID < qList.size()) {

            questions++;
            questImage.setEnabled(true);

            Log.d("CC", choice_rg.getChildCount() + "");
            int choicesNo = choice_rg.getChildCount();

            for (int j = 0; j < choicesNo; j++) {
                choice_rg.removeViewAt(0);
                Log.d("CC", "Deleted :" + j);
            }

            id = qList.get(qID).id;
            question = qList.get(qID).question;
            imageURL = qList.get(qID).imageURL;
            answer = qList.get(qID).answer;
            opts = qList.get(qID).opts;


            questView.setText(question);
            qNoView.setText("Q" + (qID + 1));
            if (!imageURL.equals("INF")) {
                new GetQuestionImage(TriviaActivity.this).execute(imageURL);
            } else {
                questImage.setImageBitmap(null);
            }
            Log.d("New CC", choice_rg.getChildCount() + "");
            for (int i = 0; i < opts.size(); i++) {
                RadioButton rb = new RadioButton(TriviaActivity.this);
                rb.setId(i);
                Log.d("CC", "Added :" + i);
                rb.setText(opts.get(i).toString());
                choice_rg.addView(rb);
            }

            Log.d("CA", "Answer:" + Oldanswer);
            Log.d("CA", "Checked:" + choice_rg.getCheckedRadioButtonId());

            if (Oldanswer - 1 == choice_rg.getCheckedRadioButtonId()) correct++;
            Log.d("CA", "Question:" + questions + "Correct:" + correct);
            Oldanswer = answer;



            Log.d("CA", "qID:" + qID);
            choice_rg.clearCheck();
            if (qID == qList.size()) qID = qID + 10;
        }

        if (qID >= qList.size()) {
            Log.d("CAgotonext", "qID:" + qID);
            Intent i = new Intent(TriviaActivity.this, StatsActivity.class);
            i.putExtra("correct", correct);
            i.putExtra("questions", questions);
            startActivityForResult(i,2);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 || requestCode == 3 || requestCode == 4) {
            if(resultCode == Activity.RESULT_OK){
                Intent i = new Intent();
                setResult(Activity.RESULT_OK,i);
                finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Intent i = new Intent();
                setResult(Activity.RESULT_CANCELED,i);
                finish();

            }
        }
        finish();

    }
}
