package com.example.kdeek.inclass09;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {
    EditText fname, lname, email, pwd, rpwd;
    Button cancel, signup;

    private final OkHttpClient client2 = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        email = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.pwd);
        rpwd = (EditText)findViewById(R.id.rpwd);

        cancel = (Button) findViewById(R.id.cancel);
        signup = (Button) findViewById(R.id.signUp);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                // intent.putExtra("object","Wrong City/State Name");
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd.getText().toString().equals(rpwd.getText().toString())) {
                    Toast.makeText(SignUp.this, "Bingo", Toast.LENGTH_SHORT).show();

                    RequestBody formBody = new FormBody.Builder()
                            .add("email", email.getText().toString())
                            .add("password", pwd.getText().toString())
                            .add("fname",fname.getText().toString())
                            .add("lname",lname.getText().toString())
                            .build();
                    Request request = new Request.Builder()
                            .url("http://ec2-54-166-14-133.compute-1.amazonaws.com/api/signup")
                            .post(formBody)
                            .build();

                    try {
                        client2.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(okhttp3.Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                                //Log.d("demo",response.body().string());
                                try {
                                    JSONObject root = new JSONObject(response.body().string());
                                    String msg = "";
                                    if (root.getString("status").equals("ok")) {
                                        Toast.makeText(SignUp.this, "Sign Up is successful", Toast.LENGTH_SHORT).show();


                                    } else {
                                        msg = root.getString("message");
                                        Toast.makeText(SignUp.this, "Sign Up failed."+msg, Toast.LENGTH_SHORT).show();
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }







                }else{
                    Toast.makeText(SignUp.this, "Password and repeat password are not same.", Toast.LENGTH_SHORT).show();
                }


                }


        });
    }
}