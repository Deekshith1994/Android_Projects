package com.example.kdeek.hw6;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kdeek on 10/19/2016.
 */
public class WeatherUtil {
    public static class JSONParseWeather {
        static ArrayList<Weather> parseWeather(String in) throws JSONException {
            ArrayList<Weather> weatherL = new ArrayList<Weather>();
            int minTemp = 0; int maxTemp = 999;
            JSONObject root = new JSONObject(in);
            if (root.getJSONArray("list") != null) {
                JSONArray weatherArray = root.getJSONArray("list");
                for (int i = 0; i < weatherArray.length(); i++) {
                    Weather w = new Weather();
                    JSONObject weatherObj = weatherArray.getJSONObject(i);
                    JSONObject weather = weatherObj.getJSONArray("weather").getJSONObject(0);
                    String main = weather.getString("main");
                    w.setClimateType(main);
                    Log.d("demo", w.climateType.toString());
                    weatherL.add(w);

                }
                return weatherL;
            }

            return  null;
        }
    }
}
