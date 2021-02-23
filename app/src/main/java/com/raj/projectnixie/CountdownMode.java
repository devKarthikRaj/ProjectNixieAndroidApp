package com.raj.projectnixie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CountdownMode extends AppCompatActivity {
    private static final String TAG = "CountdownMode";

    int setHour;
    int setMin;
    int setSec;

    EditText EThour;
    EditText ETmin;
    EditText ETsec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_mode);

        EThour = findViewById(R.id.ET_Hour);
        ETmin = findViewById(R.id.ET_Min);
        ETsec = findViewById(R.id.ET_Sec);
    }
}
