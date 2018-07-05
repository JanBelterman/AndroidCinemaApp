package com.cinema.avans.cinemaapp.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Hall implements Serializable {

    private int ID; // (PK) ID of the hall
    private int hallNr; // Hall number in cinema
    private ArrayList<SeatRow> seatRows; // All the SeatRows of this Hall

    public Hall() {

        this.ID = 0;
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
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setHallNr(int hallNr) {
        this.hallNr = hallNr;
    }
    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    // GETTERS
    public int getID() {
        return ID;
    }
    public int getHallNr() {
        return hallNr;
    }
    public void setSeatRows(ArrayList<SeatRow> seatRows) {
        this.seatRows = seatRows;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
                "HallNr: " + ID + "\n" +
                "Amount of rows: " + seatRows.size() + "\n" +
                "Amount of seats: " + amountOfSeats();
    }

}
