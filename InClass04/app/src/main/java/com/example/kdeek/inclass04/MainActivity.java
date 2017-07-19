package com.example.kdeek.inclass04;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    ExecutorService threadpool;

    int noOfPasswords, length;
    int progress;
    AlertDialog.Builder builder;
    Handler handler = new Handler();

    ArrayList<String> passwords = new ArrayList<>();
    SeekBar pCountBar, pLenBar;
    TextView pLenDisp, pCountDisp,passDisp;
    ProgressDialog newProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pCountBar = (SeekBar) findViewById(R.id.pCountBar);
        pLenBar = (SeekBar) findViewById(R.id.pLenBar);
        pCountDisp = (TextView) findViewById(R.id.pCountDisp);
        pLenDisp = (TextView) findViewById(R.id.pLenDisp);
        passDisp = (TextView) findViewById(R.id.passDisp);
        pCountBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pCountDisp.setText(i+1+"");
                noOfPasswords = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pLenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pLenDisp.setText(i+8+"");
                length = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        handler = new Handler(new Handler.Callback(){
            int pwords=0;
            @Override
            public boolean handleMessage(Message message) {
                if(message.what ==100){
                    pwords++;
                    newProgressDialog.setProgress(pwords);
                    passwords.add(message.getData().getString("Output"));

                    if(pwords >=  noOfPasswords)  {
                        pwords = 0;
                        newProgressDialog.hide();
                        vieDia();
                    }
                }
                return false;
            }
        });


    }

    protected Runnable threadrun = new Runnable() {

        private void sendPassword(String pass){
            Bundle bundle = new Bundle();
            bundle.putString("Output",pass);
            Message msg = new Message();
            msg.what = 100;
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        @Override
        public void run() {

            String p;
            p = Util.getPassword(length);

            sendPassword(p);
        }
    };
    public void asyncButton(View v) {
        for(int i = 0;i<passwords.size();i++) passwords.remove(i);
        noOfPasswords = pCountBar.getProgress()+1;
        length = pLenBar.getProgress()+8;
        new genPass().execute();

    }

    public void threadButton(View v) {
        for(int i = 0;i<passwords.size();i++) passwords.remove(i);
        noOfPasswords = pCountBar.getProgress()+1;
        length = pLenBar.getProgress()+8;
        threadpool = Executors.newFixedThreadPool(2);
        setProgBar();
        for (int i = 0;i<noOfPasswords;i++) threadpool.execute(threadrun);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void vieDia(){

        final CharSequence[] items = {"","","","","","","","","",""};
        for(int i =0; i<noOfPasswords;i++){
            items[i] = passwords.get(i).toString();
        }
        Log.d("demo",passwords.toArray().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Password")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                           try{
                               passDisp.setText(items[i]+"");
                           }catch (Exception e) {
                               passDisp.setText("Select a Password");
                           }
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void setProgBar(){
        newProgressDialog = new ProgressDialog(MainActivity.this);
        newProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        newProgressDialog.setMax(noOfPasswords);
        newProgressDialog.setCancelable(false);
        newProgressDialog.setMessage("Generating Passwords");
        newProgressDialog.show();

    }


    class genPass extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < noOfPasswords; i++) {
                String p;
                p = Util.getPassword(length);
                Log.d("demo",length+"");
                passwords.add(p);
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           setProgBar();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            newProgressDialog.hide();
            vieDia();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            newProgressDialog.setProgress(values[0]);
        }
    }
}