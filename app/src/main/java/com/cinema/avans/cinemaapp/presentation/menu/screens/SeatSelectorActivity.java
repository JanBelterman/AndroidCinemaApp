package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.RemoteHallInstanceRepository;
import com.cinema.avans.cinemaapp.domain.HallInstance;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.logic.additional.SeatSelector;
import com.cinema.avans.cinemaapp.logic.callbacks.HallInstanceCallback;
import com.cinema.avans.cinemaapp.presentation.menu.adapters.SeatGridAdapter;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatSelectorActivity extends AppCompatActivity implements HallInstanceCallback {

    private Showing showing;
    private SeatSelector seatSelector;
    private SeatGridAdapter seatGridAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);
        // Get data
        showing = (Showing) getIntent().getExtras().getSerializable("SHOWING");
        // Load hall instance
        new RemoteHallInstanceRepository(this, this).getForShowing(showing);
    }

    private void displayHallInformation() {
        HallInstance hall = seatSelector.getHallInstance();
        TextView totalCapacity = findViewById(R.id.selectorCapacityText);
        totalCapacity.setText(getString(R.string.totalCapacityWithSemiColon) + " " + hall.amountOfSeats());
        TextView availableSeats = findViewById(R.id.selectorAvailableText);
        availableSeats.setText(hall.amountOfAvailableSeats() + " " + getString(R.string.seatsStillAvailable));
    }

    private void setUpGridView() {
        // Adapter to display a cinemaHall in a gridView
        seatGridAdapter = new SeatGridAdapter(this, seatSelector.getHallInstanceToShow());
        // GridView with properties
        GridView gridView = findViewById(R.id.selectorHallGridView);
        gridView.setNumColumns(seatSelector.getHallInstance().amountOfSeatsInARow());
        gridView.setAdapter(seatGridAdapter);
        // OnItemClickListener to select a given seat
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Get clicked seat from adapter
                SeatInstance seatInstanceClicked = seatGridAdapter.getItem(position);
                // Notify selector that seat has been clicked (also pass that seat)
                seatSelector.seatClicked(seatInstanceClicked);
                // Notify the adapter that data has changes (display updated hall)
                seatGridAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createAmountOfSeatsSelection() {
        String[] amountOfSeats = new String[]{"1", "2", "3", "4", "5"};
        Spinner amountOfSeatSpinner = findViewById(R.id.selectorAmountOfSeatsSpinner);
        amountOfSeatSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, amountOfSeats));
        amountOfSeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    seatSelector.setAmount(1);
                } else if (i == 1) {
                    seatSelector.setAmount(2);
                } else if (i == 2) {
                    seatSelector.setAmount(3);
                } else if (i == 3) {
                    seatSelector.setAmount(4);
                } else if (i == 4) {
                    seatSelector.setAmount(5);
                }
                seatGridAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    private void createOkButton() {
        Button okButton = findViewById(R.id.selectorOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!seatSelector.isValid()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.select) + " " + seatSelector.getAmount() + " " + getString(R.string.seatsFirst), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SeatSelectorActivity.this, PayActivity.class);
                    intent.putExtra("SELECTED_SEATS", seatSelector.getSelectedSeats());
                    intent.putExtra("SHOWING", showing);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void hallInstanceFound(HallInstance hallInstance) {
        showing.setHallInstance(hallInstance);
        seatSelector = new SeatSelector(showing.getHallInstance(), 1); // Get hall instance from api
        // Init
        displayHallInformation();
        setUpGridView();
        createAmountOfSeatsSelection();
        createOkButton();
    }

    @Override
    public void error(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
