package com.example.saisr.trivia;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.BitSet;

/**
 * Created by kdeek on 9/23/2016.
 */
public class GetQuestionImage extends AsyncTask<String, Void, Bitmap> {
    getImageActivity activity = null;
    //ProgressDialog pd;
    public GetQuestionImage(getImageActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        activity.setImage(bm);
        activity.stopLoading();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoading();


    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        BufferedReader reader = null;
        Bitmap image = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con  = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            image = BitmapFactory.decodeStream(con.getInputStream());

            return image;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return image;
    }


    static public interface getImageActivity {

        public void setImage(Bitmap bm);
        public void progressLoading();
        public void stopLoading();

    }
}
