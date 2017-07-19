package com.example.kdeek.inclass09;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView uname, pwd;
    Button login, signUp;
    String Username;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (TextView) findViewById(R.id.username);
        pwd = (TextView) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginb);
        signUp = (Button) findViewById(R.id.signUpb);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody formBody = new FormBody.Builder()
                        .add("email", uname.getText().toString())
                        .add("password", pwd.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("http://ec2-54-166-14-133.compute-1.amazonaws.com/api/login")
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
                            //Log.d("demo",response.body().string());
                            try {
                                JSONObject root = new JSONObject(response.body().string());
                                if(root.getString("status").equals("ok")){
                                    Username = root.getString("userFname")+ " "+ root.getString("userLname");
                                    Intent i = new Intent(MainActivity.this,Chat.class);
                                    i.putExtra("name",Username);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(MainActivity.this,"Login Failed, Enter Valid Username/Password",Toast.LENGTH_SHORT);
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




    }
}
