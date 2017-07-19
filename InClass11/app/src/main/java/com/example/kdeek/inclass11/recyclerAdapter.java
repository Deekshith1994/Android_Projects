package com.example.kdeek.inclass11;

import android.content.Context;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by kdeek on 10/20/2016.
 */
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
       public String uName = "NA";


    public  interface OnItemClickListener {
        public void onItemClicked(int position);
    }

    public  interface OnCommentClickListener {
        public void onCommentClicked(int position);
    }


    OnItemClickListener listener;
    OnCommentClickListener clistener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView venue;
        TextView cat;
        ImageView icon;
        ImageView add;

        public View v;
        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;

            venue = (TextView) itemView.findViewById(R.id.vName);
            cat =  (TextView) itemView.findViewById(R.id.catName);
            icon = (ImageView) itemView.findViewById(R.id.smallPictureVenue);
            add = (ImageView) itemView.findViewById(R.id.addCity);


        }
    }

    // Store a member variable for the contacts
    private List<DataItem> mCities;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public recyclerAdapter(Context context, List<DataItem> contacts, OnItemClickListener listener) {
        mCities = contacts;
        mContext = context;
        this.listener = listener;
        this.clistener = clistener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dayView = inflater.inflate(R.layout.row_msg_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(dayView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(recyclerAdapter.ViewHolder viewHolder, final int position) {

        final DatabaseReference myRef = mDatabase.getReference("Venues");


        final DataItem city = mCities.get(position);

        TextView venueV = viewHolder.venue;
        venueV.setText(city.getName());

        TextView catV = viewHolder.cat;
        catV.setText(city.getCategory());


        ImageView iconV = viewHolder.icon;
        if(!city.getImageurl().equals(""))Picasso.with(getContext()).load(city.getImageurl()).into(iconV);



        ImageView addV = viewHolder.add;
        //if(msg.auther)
        addV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
                myRef.child(EmailPasswordActivity.curUser).child(city.getName()).setValue(city);

            }
        });
        //addV.setImageResource((R.drawable.plus));


//        viewHolder.v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemClicked(position);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }
}
