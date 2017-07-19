package com.example.kdeek.inclass09;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Chat extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    ArrayList<Message> mList;
    String Name;
    ListView msL;
    TextView nameView;
    ImageView addMsg,logout;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(getIntent().getExtras()!=null){
            Name = getIntent().getExtras().getString("name");
        }
        nameView = (TextView) findViewById(R.id.unameL);
        msL = (ListView) findViewById(R.id.listView) ;
        addMsg = (ImageView) findViewById(R.id.addMsg);
        logout = (ImageView) findViewById(R.id.logout);
        editText = (EditText) findViewById(R.id.msg);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat.this, MainActivity.class);
                // intent.putExtra("object","Wrong City/State Name");
                startActivity(intent);

            }
        });


        addMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody formBody = new FormBody.Builder()
                        .add("Type", "Text")
                        .add("Comment",editText.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("http://ec2-54-166-14-133.compute-1.amazonaws.com/api/message/add")
                        .post(formBody)
                        .build();

                try {
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(okhttp3.Call call, Response response) throws IOException {

                            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                            String errormsg = "";
                            //Log.d("demo",response.body().string());
                            try {
                                JSONObject root = new JSONObject(response.body().string());
                                if(root.getString("status").equals("ok")){
                                    // Username = root.getString("userFname")+ " "+ root.getString("userLname");
                                    //Intent i = new Intent(Chat.this,Chat.class);
                                    //i.putExtra("name",Username);
                                    //startActivity(i);
                                    Toast.makeText(Chat.this,"Message Posted succesfully",Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(Chat.this,errormsg,Toast.LENGTH_SHORT).show();
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



        nameView.setText(Name+"");

        Request request = new Request.Builder().url("http://ec2-54-166-14-133.compute-1.amazonaws.com/api/messages")
                .header("Authorization", "BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0Nzc2Mjc3MTAsImV4cCI6MTUwOTE2MzcxMCwianRpIjoiMXhhWkdBV1o3RkxpNWpYZ0RiS0dTSSIsInVzZXIiOjJ9.dKLFNjt1Uz-2cOsGYFrfMH_XfoJZSJuGK3qqMt9NgQA")
                .build();

        Response response = null;
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Log.d("demo","Failure");
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                   // Log.d("demo",response.body().string());
                    String in = response.body().string();
                    Log.d("demo",in);
                    mList = new ArrayList<Message>();
                    try {
                        JSONObject root = new JSONObject(in);
                        JSONArray messagesJSONArray = root.getJSONArray("messages");
                        Log.d("demo1",messagesJSONArray.length()+"");
                        for (int i = 0; i < messagesJSONArray.length(); i++) {
                            Log.d("demo1",messagesJSONArray.getJSONObject(i)+"");
                            JSONObject messagesJSONObject = messagesJSONArray.getJSONObject(i);
                            Message message =new Message();
                            message.setUserFname(messagesJSONObject.getString("UserFname"));
                            message.setUserLname(messagesJSONObject.getString("UserLname"));
                            message.setCreatedAt(messagesJSONObject.getString("CreatedAt"));
                            message.setId(messagesJSONObject.getInt("Id"));
                            message.setComment(messagesJSONObject.getString("Comment"));
                            message.setFileThumbnailId(messagesJSONObject.getString("FileThumbnailId"));
                            message.setType(messagesJSONObject.getString("Type"));
                            mList.add(message);
                        }
                        Log.d("demo",mList.toString());
                        Chat.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Handle UI here
                                MsgAdapter adapter = new MsgAdapter(Chat.this, R.layout.row_msg_list, mList);
                                msL.setAdapter(adapter);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListView(){

    }

}
