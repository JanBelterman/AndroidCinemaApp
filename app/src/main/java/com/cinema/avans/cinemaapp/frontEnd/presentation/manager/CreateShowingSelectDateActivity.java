package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.Calendar;

// Step 3: select date
public class CreateShowingSelectDateActivity extends AppCompatActivity {

    private Showing showing;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_select_date);

        setupActivity();

        createDateFunc();
        createTimeFunc();

        createNextButton();

    }

    private void setupActivity() {

        Intent intent = getIntent();
        showing = (Showing) intent.getSerializableExtra("SHOWING");

    }

    private void createDateFunc() {

        // Date stuff
        Button changeDateButton = findViewById(R.id.createShowingSelectDateDateChangeButton);
        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateShowingSelectDateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        final TextView dateText = findViewById(R.id.createShowingSelectDateDateText);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                // Because months start at 0
                month = month + 1;

                // Alter the date of the Showing
                Date date = showing.getDate();
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);

                // Display date
                dateText.setText(day + getString(R.string.slash) + month + getString(R.string.slash) + year);

            }};

    }

    private void createTimeFunc() {

        Button changeTimeButton = findViewById(R.id.createShowingSelectDateTimeChangeButton);
        changeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateShowingSelectDateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeSetListener,
                        hours,
                        minutes,
                        true);

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();

            }
        });

        final TextView timeText = findViewById(R.id.createShowingSelectDateTimeText);

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                // Alter the time of the Showing
                Date date = showing.getDate();
                date.setHours(hour);
                date.setMinutes(minute);

                // Display time
                // If minutes is < 10 than add an extra 0
                if (minute < 10) {
                    timeText.setText(hour + " : 0" + minute);
                } else {
                    timeText.setText(hour + " : " + minute);
                }

            }
        };

    }

    private void createNextButton() {

        Button nextButton = findViewById(R.id.createShowingSelectDateNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateShowingSelectDateActivity.this, CreateShowingConfirmActivity.class);
                intent.putExtra("SHOWING", showing);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

            }
        });

    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

}
