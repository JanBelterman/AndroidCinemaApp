package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRow implements Serializable {

    private Hall hall; // Done
    private int rowId; // Done
    private int rowNr; // Done
    private ArrayList<Seat> seats; // Done

    public SeatRow() {

        this.hall = new Hall();
        this.rowId = 0;
        this.rowNr = 0;
        this.seats = new ArrayList<>();

    }

    int amountOfSeats() {
        return seats.size();

    }

    // SETTERS
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }

    // GETTERS
    public Hall getHall() {
        return hall;
    }
    public int getRowId() {
        return rowId;
    }
    public int getRowNr() {
        return rowNr;
    }
    public ArrayList<Seat> getSeats() {
        return seats;
    }

    @Override
    public String toString() {

        return "SeatRowId: " + rowId + "\n" +
                "Within HallNr: " + hall.getHallNr() + "\n" +
                "RowNr: " + rowNr + "\n" +
                "Amount of Seats within SeatRow: " + seats.size();

    }

}
