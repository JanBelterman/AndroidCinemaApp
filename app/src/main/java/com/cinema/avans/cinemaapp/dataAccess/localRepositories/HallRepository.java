package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.Hall;
import com.cinema.avans.cinemaapp.domain.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallRepository {

    DatabaseManager databaseManager;

    public HallRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    // Creates an entire Hall (SeatRows and Seats included)
    public void createHall(Hall hall) {

        // Add Hall
        databaseManager.createHall(hall);

        // Also add the SeatRows within the Hall
        for (SeatRow seatRow : hall.getSeatRows()) {
            new SeatRowRepository(databaseManager).createSeatRow(seatRow);

        }

    }

    // Returns all Halls from database
    public ArrayList<Hall> getAllHalls() {

        // Halls without SeatRows and Seats
        ArrayList<Hall> halls = databaseManager.getAllHalls();

        // Getting the SeatRows and Seats
        for (Hall hall : halls) {
            hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        }

        return halls;

    }

}
