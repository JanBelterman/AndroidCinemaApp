package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowRepository {

    DatabaseManager databaseManager;

    public SeatRowRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeatRow(SeatRow seatRow) {

        // Log
        Log.i("SeatRowRepository", "Creating SeatRow:\n" + seatRow);

        // Add the seat row
        seatRow = databaseManager.createSeatRow(seatRow);

        // Also add the Seats within the SeatRow
        for (Seat seat : seatRow.getSeats()) {
            new SeatRepository(databaseManager).createSeat(seat);

        }

    }

    ArrayList<SeatRow> getSeatRows(Hall hall) {

        // Get SeatRows without Seats
        ArrayList<SeatRow> seatRows = databaseManager.getSeatRows(hall.getHallNr());

        // Getting the Seats
        for (SeatRow seatRow : seatRows) {
            seatRow.setSeats(new SeatRepository(databaseManager).getSeats(seatRow));

        }

        return seatRows;

    }

}
