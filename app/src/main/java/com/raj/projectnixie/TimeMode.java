package com.raj.projectnixie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class TimeMode extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TimeMode";

    ImageButton manualTimeBtn;
    ImageButton systemTimeBtn;

    TextView timeDisplayTV;

    MaterialTimePicker materialTimePicker;

    int setHour;
    int setMin;
    int setSec;

    GetModifiedSystemTime getModifiedSystemTime = new GetModifiedSystemTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_mode);

        manualTimeBtn = findViewById(R.id.Button_Manual_Time);
        systemTimeBtn = findViewById(R.id.Button_System_Time);
        timeDisplayTV = findViewById(R.id.TV_Time_Display);

        manualTimeBtn.setOnClickListener(this);
        systemTimeBtn.setOnClickListener(this);

        timeDisplayTV.setText(R.string.time_display_initial_text);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Button_Manual_Time:
                //Configure and Initialize TimePicker
                materialTimePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(00)
                        .setTitleText("Select Time to Display on Nixie")
                        .build();

                materialTimePicker.show(getSupportFragmentManager(), TAG);

                //OnClickListeners for various TimePicker buttons
                materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String displayTimeString = getTimeFromTimePicker();
                        Log.d(TAG,"Time Picked Through TimePicker Fragment");
                        timeDisplayTV.setText(displayTimeString);
                    }
                });

                materialTimePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.d(TAG, "Time Not Picked, TimePicker Fragment Dismissed");
                        timeDisplayTV.setText(R.string.time_display_initial_text);
                    }
                });
                break;

            case R.id.Button_System_Time:
                String displayTimeString = getSystemTime();
                timeDisplayTV.setText(displayTimeString);
                break;
        }
    }

    //This method gets the time from the TimePicker and formats it for further use.
    // The method also updates the setHour, setMin, setSec variables with the current time... These variables will be sent to the hardware
    public String getTimeFromTimePicker() {
        //Get the picked time from TimePicker
        int pickedHour = materialTimePicker.getHour();
        int pickedMin = materialTimePicker.getMinute();
        int preSelectedSecond = 0;
        Log.d(TAG, "Picked Hour: " + pickedHour + " Picked Minute: " + pickedMin + " Pre Selected Second: 0");

        //Format the picked time
        String formattedHourString;
        String formattedMinString;
        String formattedSecString = "00";
        //Format Hour:
        if(pickedHour<10) {
            formattedHourString = "0" + pickedHour;
        }
        else {
            formattedHourString = String.valueOf(pickedHour);
        }
        //Format Min:
        if(pickedMin<10) {
            formattedMinString = "0" + pickedMin;
        }
        else {
            formattedMinString = String.valueOf(pickedMin);
        }

        //Put together the formatted time string:
        String formattedManualTimeString = formattedHourString + ":" + formattedMinString + ":" + formattedSecString;

        //Pass time in int format to public variables to be sent to bluetooth write thread
        setHour = pickedHour;
        setMin = pickedMin;
        setSec = preSelectedSecond;

        //Return formattedTimeSring to be displayed in UI
        return formattedManualTimeString;
    }

    public String getSystemTime() {

        //For Min
        String stringMin = getModifiedSystemTime.getCurrentSysMin();
        setMin = Integer.parseInt(stringMin);

        //For Hour
        String stringHour = getModifiedSystemTime.getCurrentSysHour();
        setHour = Integer.parseInt(stringHour);

        String formattedSysTimeString = setHour + ":" + setMin + ":" + "00";
        return formattedSysTimeString;
    }
}
