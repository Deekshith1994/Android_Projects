package com.example.kdeek.inclass07;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdeek on 10/3/2016.
 */
public class ListAdapter extends ArrayAdapter<Item> {



        List<Item> itemList = new ArrayList<>();
        Context mContext;
        int mResource;
        String s;

        public ListAdapter(MainActivity context, int resource, ArrayList<Item> itemList) {
            super(context, resource, itemList);
            this.itemList = itemList;
            this.mContext=context;
            this.mResource=resource;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("demo123","Working");
            if(convertView==null)
            {
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(mResource, parent, false);
            }

            Item item = itemList.get(position);


            String url = item.getsURL();

            ImageView iView = (ImageView) convertView.findViewById(R.id.iView);
            TextView textView = (TextView) convertView.findViewById(R.id.titleView);

            Picasso.with(mContext).load(url).into(iView);
            textView.setText(item.getTittle());

//            if((!s.equals(""))){
//                if(item.getTittle().toLowerCase().trim().contains(s.toLowerCase().trim())){
//                    convertView.setBackgroundColor(Color.GREEN);
//                }
//            }
//
//            else
            //convertView.setBackgroundColor(Color.WHITE);
            return convertView;

        }

}
