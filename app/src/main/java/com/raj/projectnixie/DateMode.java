package com.raj.projectnixie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class DateMode extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DateMode";

    ImageButton manualDateBtn;
    ImageButton systemDateBtn;

    TextView dateDisplayTV;

    int setYear;
    int setMonth;
    int setDay;

    GetModifiedSystemTime getModifiedSystemTime = new GetModifiedSystemTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_mode);

        manualDateBtn= findViewById(R.id.Button_Manual_Date);
        systemDateBtn = findViewById(R.id.Button_System_Date);
        dateDisplayTV = findViewById(R.id.TV_Date_Display);

        manualDateBtn.setOnClickListener(this);
        systemDateBtn.setOnClickListener(this);

        dateDisplayTV.setText(R.string.date_display_initial_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_Manual_Date:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), TAG);
                break;

            case R.id.Button_System_Date:
                String displayTimeString = getSystemDate();
                dateDisplayTV.setText(displayTimeString);
                break;
        }
    }

    //This method is called when the user presses the "Ok" button in the date picker fragment that just popped up
    //This method gets the date from the DatePicker and formats it for further use
    //This method also updates the setYear, setMonth, setDay variables with the current time... These variables will be sent to the hardware
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setYear = year;
        setMonth = month;
        setDay = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String setDateString = String.valueOf(calendar.get(Calendar.MONTH)) + "/" +  String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/" +  String.valueOf(calendar.get(Calendar.YEAR));
        dateDisplayTV.setText(setDateString);
    }

    private String getSystemDate() {
        //For Day
        String stringDay = getModifiedSystemTime.getCurrentDay();
        setDay = Integer.parseInt(stringDay);

        //For Month
        String stringMonth = getModifiedSystemTime.getCurrentMonth();
        setDay = Integer.parseInt(stringMonth);

        //For Year
        String stringYear = getModifiedSystemTime.getCurrentYear();
        setDay = Integer.parseInt(stringYear);

        //Format the string to be displayed to the user
        return stringMonth + "/" + stringDay + "/" + stringDay;
    }
}
