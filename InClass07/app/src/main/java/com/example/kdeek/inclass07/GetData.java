package com.example.kdeek.inclass07;

import android.content.Context;
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
 * Created by kdeek on 10/3/2016.
 */
public class GetData  extends AsyncTask<String, Void, ArrayList<Item>>{
    gData activity = null;

    public GetData(gData activity) {

        this.activity = activity;
    }

    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        BufferedReader reader = null;
        ArrayList<Item> iList = new ArrayList<>();
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
                Log.d("demo",sb.toString());
                iList = DataUtil.ItemParser.parseItems(sb.toString());
                return iList;
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

    @Override
    protected void onPostExecute(ArrayList<Item> itemList) {
        super.onPostExecute(itemList);
        activity.setData(itemList);
        activity.stopLoading();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoading();
    }

    static  public interface gData {
        public void setData(ArrayList<Item> itemList);
        public void progressLoading();
        public void stopLoading();

    }
}
