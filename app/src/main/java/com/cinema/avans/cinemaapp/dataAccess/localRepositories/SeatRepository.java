package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.domain.cinema.SeatRow;

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

        // Create Seat
        databaseManager.createSeat(seat);

    }

    ArrayList<Seat> getSeats(SeatRow seatRow) {

        return databaseManager.getSeats(seatRow.getRowId());

    }

}
