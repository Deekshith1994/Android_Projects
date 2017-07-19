package com.example.kdeek.calculator;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button but0,butEq,butDot,but1,but2,but3,but4,but5,but6,but7,but8,but9;
    private Button butDel, butAdd, butSub, butPro, butDiv;
    private TextView textView, textView2;

    ArrayList<String> arrayList = new ArrayList<String>();
    String tempstring = "";
    String string = "";
    int Result = 0;
    int A = arrayList.size();



    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        but0 = (Button)findViewById(R.id.but0);
        but1 = (Button)findViewById(R.id.but1);
        but2 = (Button)findViewById(R.id.but2);
        but3 = (Button)findViewById(R.id.but3);
        but4 = (Button)findViewById(R.id.but4);
        but5 = (Button)findViewById(R.id.but5);
        but6 = (Button)findViewById(R.id.but6);
        but7 = (Button)findViewById(R.id.but7);
        but8 = (Button)findViewById(R.id.but8);
        but9 = (Button)findViewById(R.id.but9);
        butEq = (Button)findViewById(R.id.butEq);
        butDot = (Button)findViewById(R.id.butDot);
        butDel = (Button)findViewById(R.id.butDel);
        butAdd = (Button)findViewById(R.id.butAdd);
        butSub = (Button)findViewById(R.id.butSub);
        butPro = (Button)findViewById(R.id.butPro);
        butDiv = (Button)findViewById(R.id.butDiv);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);


        but0.setInputType(Integer.parseInt("0"));
        but1.setInputType(Integer.parseInt("1"));
        but2.setInputType(Integer.parseInt("2"));
        but3.setInputType(Integer.parseInt("3"));
        but4.setInputType(Integer.parseInt("4"));
        but5.setInputType(Integer.parseInt("5"));
        but6.setInputType(Integer.parseInt("6"));
        but7.setInputType(Integer.parseInt("7"));
        but8.setInputType(Integer.parseInt("8"));
        but9.setInputType(Integer.parseInt("9"));
        butDot.setText(".");
        butDiv.setText("/");
        butPro.setText("*");
        butAdd.setText("+");
        butSub.setText("-");

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    public void onClick1(View v) {



        Button button = (Button) v;

        tempstring =  button.getText().toString();

        if(!tempstring.contains("+") && !tempstring.contains("-") && !tempstring.contains("*") && !tempstring.contains("/")){
            string = string + tempstring;
            if (arrayList.size() > 0)
                arrayList.remove(arrayList.size() - 1);

            arrayList.add(string);
        }
        else {
            arrayList.add(tempstring);
            arrayList.add(tempstring);
            string = "";
        }
        textView2.setText(arrayList.toString());

    }


    @Override
    public void onClick(View v) {

            while (A!=1){

                if (A>3){

                    if (arrayList.get(3).contains("*") || arrayList.get(3).contains("/")){
                        if (arrayList.get(3).contains("*"))  Result = Integer.parseInt(arrayList.get(2))*Integer.parseInt(arrayList.get(4));
                        if (arrayList.get(3).contains("/"))  Result = Integer.parseInt(arrayList.get(2))/Integer.parseInt(arrayList.get(4));
                        arrayList.remove(2);
                        arrayList.remove(2);
                        arrayList.remove(2);
                        arrayList.add(2, Integer.toString(Result));
                        A = arrayList.size();
                    }
                    else {
                        if (arrayList.get(1).contains("+"))  Result = Integer.parseInt(arrayList.get(0))+Integer.parseInt(arrayList.get(2));
                        if (arrayList.get(1).contains("-"))  Result = Integer.parseInt(arrayList.get(0))-Integer.parseInt(arrayList.get(2));
                        if (arrayList.get(1).contains("*"))  Result = Integer.parseInt(arrayList.get(0))*Integer.parseInt(arrayList.get(2));
                        if (arrayList.get(1).contains("/"))  Result = Integer.parseInt(arrayList.get(0))/Integer.parseInt(arrayList.get(2));
                        arrayList.remove(0);
                        arrayList.remove(0);
                        arrayList.remove(0);
                        arrayList.add(0, Integer.toString(Result));
                        A = arrayList.size();
                    }
                }

                else{
                    if (arrayList.get(1).contains("+"))  Result = Integer.parseInt(arrayList.get(0))+Integer.parseInt(arrayList.get(2));
                    if (arrayList.get(1).contains("-"))  Result = Integer.parseInt(arrayList.get(0))-Integer.parseInt(arrayList.get(2));
                    if (arrayList.get(1).contains("*"))  Result = Integer.parseInt(arrayList.get(0))*Integer.parseInt(arrayList.get(2));
                    if (arrayList.get(1).contains("/"))  Result = Integer.parseInt(arrayList.get(0))/Integer.parseInt(arrayList.get(2));
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.add(0, Integer.toString(Result));
                    A = arrayList.size();
                }
            }
            textView.setText(Result+"");
    }
}
