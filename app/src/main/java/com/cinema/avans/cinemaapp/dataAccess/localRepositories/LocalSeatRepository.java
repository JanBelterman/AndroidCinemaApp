package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.Seat;
import com.cinema.avans.cinemaapp.domain.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LocalSeatRepository {

    private DatabaseManager databaseManager;

    public LocalSeatRepository(DatabaseManager databaseManager) {
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
