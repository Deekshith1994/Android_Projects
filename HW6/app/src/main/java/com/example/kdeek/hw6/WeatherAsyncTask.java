package com.example.kdeek.hw6;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kdeek on 10/19/2016.
 */
public class WeatherAsyncTask extends AsyncTask<String, Void, ArrayList<Weather>> {
    getWeather activity = null;
    ProgressDialog pd;
    AlertDialog.Builder builder;

    public WeatherAsyncTask(getWeather activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status= con.getResponseCode();
            if(status== HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while(line!=null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }

                return  WeatherUtil.JSONParseWeather.parseWeather(stringBuilder.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoading();

    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weatherL) {
        super.onPostExecute(weatherL);
        activity.stopProgress();
        activity.setWeatherL(weatherL);

    }

    public static interface getWeather{
        public void progressLoading();
        public void stopProgress();
        public void setWeatherL(ArrayList<Weather> weatherL);

    }


}
