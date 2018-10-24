package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.Hall;
import com.cinema.avans.cinemaapp.domain.Seat;
import com.cinema.avans.cinemaapp.domain.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class LocalSeatRowRepository {

    DatabaseManager databaseManager;

    public LocalSeatRowRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeatRow(SeatRow seatRow) {

        // Add the seat row
        seatRow = databaseManager.createSeatRow(seatRow);

        // Also add the Seats within the SeatRow
        for (Seat seat : seatRow.getSeats()) {
            new LocalSeatRepository(databaseManager).createSeat(seat);

        }

    }

    ArrayList<SeatRow> getSeatRows(Hall hall) {

        // Get SeatRows without Seats
        ArrayList<SeatRow> seatRows = databaseManager.getSeatRows(hall.getHallNr());

        // Getting the Seats
        for (SeatRow seatRow : seatRows) {
            seatRow.setSeats(new LocalSeatRepository(databaseManager).getSeats(seatRow));

        }

        return seatRows;

    }

}
