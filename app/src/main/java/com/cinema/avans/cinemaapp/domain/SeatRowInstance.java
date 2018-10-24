package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowInstance implements Serializable {

    private HallInstance hallInstance; // Done
    private int ID; // Done
    private SeatRow seatRow; // Done
    private ArrayList<SeatInstance> seatInstances; // Done

    public SeatRowInstance() {

        this.hallInstance = new HallInstance();
        this.ID = 0;
        this.seatRow = new SeatRow();
        this.seatInstances = new ArrayList<>();

    }

    // Returns the amount of seatInstances in the row
    int getAmountOfSeats() {
        return seatInstances.size();

    }

    // Returns the amount of actual seats (excluding gaps)
    int getAmountOfActualSeats() {

        int amountOfActualSeats = 0;

        for (SeatInstance seat : seatInstances) {

            if (seat.getStatus() != SeatStatus.GAP) {

                amountOfActualSeats ++;

            }

        }

        return amountOfActualSeats;

    }

    // Returns the amount of available seats
    int getAmountOfAvailableSeats() {

        int amountOfAvailableSeats = 0;

        for (SeatInstance seatInstances : seatInstances) {

            if (seatInstances.getStatus() == SeatStatus.AVAILABLE) {

                amountOfAvailableSeats ++;

            }
        }

        return amountOfAvailableSeats;

    }

    // SETTERS
    public void setHallInstance(HallInstance hallInstance) {
        this.hallInstance = hallInstance;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setSeatRow(SeatRow seatRow) {
        this.seatRow = seatRow;

    }
    public void setSeatInstances(ArrayList<SeatInstance> seatInstances) {
        this.seatInstances = seatInstances;
    }

    // GETTERS
    public HallInstance getHallInstance() {
        return hallInstance;
    }
    public int getID() {
        return ID;
    }
    public SeatRow getSeatRow() {
        return seatRow;
    }
    public ArrayList<SeatInstance> getSeatInstances() {
        return this.seatInstances;
    }

    @Override
    public String toString() {

        return "SeatRowInstanceId: " + ID + "\n" +
                "Is an instance of: " + seatRow + "\n" +
                "HallInstance: " + hallInstance + "\n" +
                "Amount of Seats: " + getAmountOfActualSeats();


    }

}
