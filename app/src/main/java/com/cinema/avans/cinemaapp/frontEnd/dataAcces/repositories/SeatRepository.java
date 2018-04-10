package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class SeatRepository {

    private DatabaseManager databaseManager;

    public SeatRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeat(Seat seat) {

        // Log
        Log.i("SeatRepository", "Creating Seat:\n" + seat);
        // Create Seat
        databaseManager.createSeat(seat);

    }

    ArrayList<Seat> getSeats(SeatRow seatRow) {

        return databaseManager.getSeats(seatRow.getRowId());

    }

}
