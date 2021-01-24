package com.raj.projectnixie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";

    private static int SPLASH_TIME_OUT=5000;
    private boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Establishing connection with Nixie Hardware
        Log.d(TAG,"Establishing connection with ESP32");
        //tbc
        connectionStatus = true; //for testing purposes
        if(connectionStatus == true) {
            //---Splash Screen---
            Log.d(TAG,"Connection successfully established with ESP32");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, Home.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        else {
            Log.d(TAG,"Unable to establish connection with ESP32");
            Toast.makeText(this,"Unable to establish connection with ESP32",Toast.LENGTH_LONG);
        }
    }
}
