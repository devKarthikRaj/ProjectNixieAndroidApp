package com.raj.projectnixie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class Home extends Activity implements OnClickListener {
    private static final String TAG = "Home";

    ImageButton timeModeBtn;
    ImageButton dateModeBtn;
    ImageButton CountdownModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timeModeBtn = findViewById(R.id.Time_Mode_Button);
        dateModeBtn = findViewById(R.id.Date_Mode_Button);
        CountdownModeBtn = findViewById(R.id.Countdown_Mode_Button);

        timeModeBtn.setOnClickListener(this);
        dateModeBtn.setOnClickListener(this);
        CountdownModeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Time_Mode_Button:
                Log.d(TAG, "Time Mode Button Clicked");
                startActivity(new Intent(Home.this, TimeMode.class));
                break;
            case R.id.Date_Mode_Button:
                Log.d(TAG, "Date Mode Button Clicked");
                startActivity(new Intent(Home.this, DateMode.class));
                break;
            case R.id.Countdown_Mode_Button:
                Log.d(TAG, "Countdown Mode Button Clicked");
                startActivity(new Intent(Home.this, CountdownMode.class));
                break;
        }
    }
}
