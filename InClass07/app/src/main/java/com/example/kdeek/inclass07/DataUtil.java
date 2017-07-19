package com.example.kdeek.inclass07;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kdeek on 10/3/2016.
 */
public class DataUtil {

    static public class ItemParser {

        static ArrayList<Item> parseItems(String in) throws JSONException {

            ArrayList<Item> iList = new ArrayList<>();

            JSONObject root1 = new JSONObject(in);

            JSONObject root = root1.getJSONObject("feed");
            JSONArray items = root.getJSONArray("entry");

            for(int i = 0; i < items.length(); i++){
                String tittle = "";
                String desc = "";
                String publish = "";
                String sURL = "";
                String link = "";
                String lURL = "";


                JSONObject itemobj = items.getJSONObject(i);
                JSONObject nameobj = itemobj.getJSONObject("im:name");
                tittle = nameobj.getString("label");




                JSONArray imagesArray = itemobj.getJSONArray("im:image");

                    JSONObject iObj = imagesArray.getJSONObject(0);
                    sURL = iObj.getString("label");
                    iObj = imagesArray.getJSONObject(2);
                    lURL = iObj.getString("label");

                JSONObject descobj = itemobj.getJSONObject("summary");
                desc = descobj.getString("label");

                JSONObject dateObj = itemobj.getJSONObject("im:releaseDate");
                link = dateObj.getString("label");

                JSONObject dateSFObj = dateObj.getJSONObject("attributes");
                publish = dateSFObj.getString("label");

                Log.d("demo1",tittle.toString());
                Log.d("demo1",sURL.toString());
                Item item = new Item(tittle, desc, publish, sURL, link, lURL);
                iList.add(item);
            }


            return iList;
        }

    }

}
