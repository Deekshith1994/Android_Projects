package com.example.kdeek.inclass11;

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
 * Created by kdeek on 10/24/2016.
 */
public class GetDataAsync extends AsyncTask<String, Void, ArrayList<DataItem>> {
    getActivity activity = null;

    public GetDataAsync(getActivity activity) {
        this.activity = activity;
    }



    @Override
    protected ArrayList<DataItem> doInBackground(String... strings) {
        BufferedReader reader = null;
        ArrayList<DataItem> dataItemArrayList = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con  = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
           // if(statusCode == HttpURLConnection.HTTP_OK){
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while(line != null){
                    sb.append(line);
                    Log.d("demo", line);
                    line = reader.readLine();

                }
                Log.d("demo",sb.toString());
                dataItemArrayList = DataItemUtil.DataParser.parseData(sb.toString());
                return dataItemArrayList;
            //}

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
    protected void onPostExecute(ArrayList<DataItem> dataItems) {

        super.onPostExecute(dataItems);
        activity.setData(dataItems);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoading();
    }

    static public interface getActivity {

        public void progressLoading();
        public void setData(ArrayList<DataItem> list);

    }



}
