package com.cinema.avans.cinemaapp.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.domain.cinema.SeatValue;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class CreateHallActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hall);

        setupActivity();

        createSpinners();

        createSaveButton();

    }

    private void setupActivity() {

        repositoryFactory = new RepositoryFactory(getApplicationContext());

    }

    private void createSpinners() {

        // Amount of rows spinner
        final Integer[] rows = new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16};
        Spinner rowSpinner = findViewById(R.id.createHallAmountOfRowsSpinner);
        rowSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, rows));

        // Amount of seats spinner
        final Integer[] seats = new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16};
        Spinner seatSpinner = findViewById(R.id.createHallAmountOfSeatsSpinner);
        seatSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, seats));

    }

    private void createSaveButton() {

        Button saveButton = findViewById(R.id.createHallSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check user input
                if (!validateInput()) {
                    return;
                }

                // Get hallNr
                EditText hallNrInput = findViewById(R.id.createHallHallNrInput);
                int hallNr = Integer.parseInt(hallNrInput.getText().toString());
                // Get amount of rows
                Spinner amountOfRowsSpinner = findViewById(R.id.createHallAmountOfRowsSpinner);
                int amountOfRows = (int) amountOfRowsSpinner.getSelectedItem();
                // Get amount of seats in each row
                Spinner amountOfSeatsInEachRowSpinner = findViewById(R.id.createHallAmountOfSeatsSpinner);
                int amountOfSeatsInEachRow = (int) amountOfSeatsInEachRowSpinner.getSelectedItem();

                // Create Hall
                Hall hall = createHall(hallNr, amountOfRows, amountOfSeatsInEachRow);
                // Add Hall to database
                repositoryFactory.getHallRepository().createHall(hall);
                // Show message
                Toast.makeText(getApplicationContext(), R.string.hallAdded, Toast.LENGTH_SHORT).show();
                // Finish activity
                finish();

            }
        });

    }

    private boolean validateInput() {

        // Check hallNr
        EditText hallNrInput = findViewById(R.id.createHallHallNrInput);
        String hallNrStr = String.valueOf(hallNrInput.getText());
        if (!hallNrStr.matches("\\d+")) {
            return false;
        }
        int hallNr = Integer.parseInt(hallNrStr);
        if (hallNr < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.hallNrToLowError), Toast.LENGTH_SHORT).show();
            return false;
        } else if (hallNr > 20) {
            Toast.makeText(getApplicationContext(), getString(R.string.hallNrToHighError), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public Hall createHall(int hallNr, int amountOfRows, int amountOfSeatsInEachRow) {

        Hall hall = new Hall();
        hall.setHallNr(hallNr);
        ArrayList<SeatRow> seatRows = new ArrayList<>();
        for (int i = 0; i < amountOfRows; i++) {
            SeatRow seatRow = new SeatRow();
            seatRow.setRowId(i);
            seatRow.setRowNr(i + 1);
            seatRow.setHall(hall);

            ArrayList<Seat> seats = new ArrayList<>();
            for (int j = 0; j < amountOfSeatsInEachRow; j++ ) {
                Seat seat = new Seat();
                seat.setSeatRow(seatRow);
                seat.setSeatValue(SeatValue.OK);
                seat.setSeatNr(j + 1);
                seats.add(seat);

            }
            seatRow.setSeats(seats);
            seatRows.add(seatRow);

        }
        hall.setSeatRows(seatRows);

        return hall;

    }

}
