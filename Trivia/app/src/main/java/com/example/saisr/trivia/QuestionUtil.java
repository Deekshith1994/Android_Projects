package com.example.saisr.trivia;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kdeek on 9/23/2016.
 */
public class QuestionUtil {

    static public class QuestionParser {

        static ArrayList<Question> parseQuestions(String in) throws JSONException {
            ArrayList<Question> qList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray questions = root.getJSONArray("questions");

            for(int i = 0; i < questions.length(); i++){
                int id = 0;
                String question = "";
                String imageURL = "";
                int answer = 0;
                ArrayList<String> opts = new ArrayList<>();

                /*

                "questions": [
                          {
                            "id": "0",
                            "text": "Who is the first President of the United States of America?",
                            "image": "http://dev.theappsdr.com/apis/trivia_json/photos/georgewashington.png",
                            "choices": {
                              "choice": [
                                "George Washington",
                                "Thomas Jefferson",
                                "James Monroe",
                                "John Adams",
                                "Barack Obama",
                                "George Bush",
                                "Abraham Lincoln",
                                "John F. Kennedy"
                              ],
                              "answer":"1"
                            }
                          },
                 */


                JSONObject questJSONobj = questions.getJSONObject(i);
                id = questJSONobj.getInt("id");

                try{
                    imageURL = questJSONobj.getString("image");
                }catch (Exception e){
                    imageURL = "INF";
                }
                question = questJSONobj.getString("text");

                JSONObject choicesJSONObject = questJSONobj.getJSONObject("choices");
                answer = choicesJSONObject.getInt("answer");

                JSONArray choicesArray = choicesJSONObject.getJSONArray("choice");
                Log.d("Demo",choicesArray.toString());
                for(int j = 0; j < choicesArray.length(); j++){
                    opts.add(choicesArray.getString(j));
                }
                Log.d("Demo",opts.toString());

                Question quest = new Question(id,question,imageURL,opts,answer);
                qList.add(quest);
            }


            return qList;
        }

    }
}
