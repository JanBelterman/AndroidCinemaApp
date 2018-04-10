package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowInstance implements Serializable {

    private HallInstance hallInstance; // Done
    private int seatRowInstanceId; // Done
    private SeatRow seatRow; // Done
    private ArrayList<SeatInstance> seatInstances; // Done

    public SeatRowInstance() {

        this.hallInstance = null;
        this.seatRowInstanceId = 0;
        this.seatRow = null;
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
    public void setSeatRowInstanceId(int seatRowInstanceId) {
        this.seatRowInstanceId = seatRowInstanceId;
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
    public int getSeatRowInstanceId() {
        return seatRowInstanceId;
    }
    public SeatRow getSeatRow() {
        return seatRow;
    }
    public ArrayList<SeatInstance> getSeatInstances() {
        return this.seatInstances;
    }

    @Override
    public String toString() {

        return "SeatRowInstanceId: " + seatRowInstanceId + "\n" +
                "Is an instance of: " + seatRow + "\n" +
                "HallInstance: " + hallInstance + "\n" +
                "Amount of Seats: " + getAmountOfActualSeats();


    }

}
