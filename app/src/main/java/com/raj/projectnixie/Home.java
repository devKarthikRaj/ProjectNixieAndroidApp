package com.raj.projectnixie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class Home extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "Home";

    ImageButton timeModeBtn;
    ImageButton dateModeBtn;
    ImageButton countdownModeBtn;
    ImageButton ledControlBtn;
    ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timeModeBtn = findViewById(R.id.Button_Time_Mode);
        dateModeBtn = findViewById(R.id.Button_Date_Mode);
        countdownModeBtn = findViewById(R.id.Button_Countdown_Mode);
        ledControlBtn = findViewById(R.id.Button_LED_Control);
        settingsBtn = findViewById(R.id.Button_Status_Monitor);

        timeModeBtn.setOnClickListener(this);
        dateModeBtn.setOnClickListener(this);
        countdownModeBtn.setOnClickListener(this);
        ledControlBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Button_Time_Mode:
                Log.d(TAG, "Time Mode Button Clicked");
                startActivity(new Intent(Home.this, TimeMode.class));
                break;
            case R.id.Button_Date_Mode:
                Log.d(TAG, "Date Mode Button Clicked");
                startActivity(new Intent(Home.this, DateMode.class));
                break;
            case R.id.Button_Countdown_Mode:
                Log.d(TAG, "Countdown Mode Button Clicked");
                startActivity(new Intent(Home.this, CountdownMode.class));
                break;
            case R.id.Button_LED_Control:
                Log.d(TAG, "LED Control Button Clicked");
                startActivity(new Intent(Home.this, LedControl.class));
                break;
            case R.id.Button_Status_Monitor:
                Log.d(TAG,"Status Monitor Button Clicked");
                startActivity(new Intent(Home.this, StatusMonitor.class));
                break;
        }
    }
}
