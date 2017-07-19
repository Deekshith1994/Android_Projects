package com.example.kdeek.inclass09;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saisr on 10/31/2016.
 */
public class MsgAdapter extends ArrayAdapter<Message> {
    List<Message> data;
    Context context;
    String msgType = "TEXT";
    int resource;
    int i = 0;
    // WeatherAsynTask.GetContext cont = (WeatherAsynTask.GetContext) context;
    public MsgAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data=objects;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }
        Message msg = data.get(position);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        String imageUrl = msg.getFileThumbnailId();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.msgImg);
        TextView msgText = (TextView) convertView.findViewById(R.id.msgText);
        name.setText(msg.getUserFname()+" "+msg.getUserLname());
        time.setText(msg.getCreatedAt());
        if(msgType.equals("TEXT")){
            msgText.setText(msg.getComment());
            imageView.setVisibility(View.INVISIBLE);
        }else {
            imageView.setVisibility(View.VISIBLE);
            msgText.setText("Image");
        }
        return convertView;

    }
}
