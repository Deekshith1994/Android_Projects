package com.example.kdeek.inclass11;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kdeek on 10/24/2016.
 */
public class DataItemUtil {

    static public class DataParser {

        static ArrayList<DataItem> parseData(String in) throws JSONException {
            ArrayList<DataItem> iList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONObject feed = root.getJSONObject("response");
            JSONArray entries = feed.getJSONArray("venues");
            for(int i = 0; i < entries.length(); i++){

                JSONObject entry = entries.getJSONObject(i);

                String name = entry.getString("name");

                JSONObject location = entry.getJSONObject("location");
                String address = location.getString("formattedAddress");

                JSONArray cats = entry.getJSONArray("categories");
                JSONObject cat = cats.getJSONObject(0);
                String category = cat.getString("shortName");

                JSONObject stats = entry.getJSONObject("stats");
                String checkins = stats.getString("checkinsCount");
                String users = stats.getString("usersCount");

                JSONObject hn = entry.getJSONObject("hereNow");
                String count = hn.getString("count");

//                public DataItem(String name, String address, String category, String imageurl, String largeimageurl, String checkins, String users, String hereNow) {

                    DataItem item = new DataItem(name,address,category,"","",checkins,users,count);

                iList.add(item);
            }
            Log.d("demo1",iList.toString());

            return iList;
        }

    }
}
