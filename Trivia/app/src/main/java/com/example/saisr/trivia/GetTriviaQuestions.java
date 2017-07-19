package com.example.saisr.trivia;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kdeek on 9/23/2016.
 */
public class GetTriviaQuestions extends AsyncTask<String, Void, ArrayList<Question>> {
    getQuestionsActivity activity = null;

    public GetTriviaQuestions(getQuestionsActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
        Log.d("ArrayList",questions.toString());
        activity.startTrivia(questions);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoading();
    }

    @Override
    protected ArrayList<Question> doInBackground(String... strings) {
        BufferedReader reader = null;
        ArrayList<Question> qList = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con  = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while(line != null){
                    sb.append(line);
                    Log.d("demo", line);
                    line = reader.readLine();

                }
                qList = QuestionUtil.QuestionParser.parseQuestions(sb.toString());
                return qList;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }


    static public interface getQuestionsActivity {

        public void startTrivia(ArrayList<Question> quest);
        public void progressLoading();

    }
}
