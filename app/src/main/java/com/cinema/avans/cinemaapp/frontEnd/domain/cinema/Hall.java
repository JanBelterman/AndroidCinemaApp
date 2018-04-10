package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Hall implements Serializable {

    private int hallNr; // PK Hall number in cinema
    private ArrayList<SeatRow> seatRows; // All the SeatRows of this Hall

    public Hall() {

        this.hallNr = 0;
        this.seatRows = new ArrayList<>();

    }

    public int amountOfSeats() {

        int amountOfSeats = 0;

        for (SeatRow seatRow : seatRows) {

            amountOfSeats += seatRow.amountOfSeats();

        }

        return amountOfSeats;

    }

    // SETTERS
    public void setHallNr(int hallNr) {
        this.hallNr = hallNr;
    }
    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    // GETTERS
    public void setSeatRows(ArrayList<SeatRow> seatRows) {
        this.seatRows = seatRows;
    }
    public int getHallNr() {
        return hallNr;
    }

    @Override
    public String toString() {

        return "HallNr: " + hallNr + "\n" +
                "Amount of rows: " + seatRows.size() + "\n" +
                "Amount of seats: " + amountOfSeats();

    }

}
