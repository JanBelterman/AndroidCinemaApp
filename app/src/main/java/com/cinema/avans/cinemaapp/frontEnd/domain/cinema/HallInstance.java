package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class HallInstance implements Serializable {

    private int hallInstanceId; // Done
    private Hall hall; // Done
    private ArrayList<SeatRowInstance> seatRowInstances; // Done

    public HallInstance() {

        this.hallInstanceId = 0;
        this.hall = new Hall();
        this.seatRowInstances = new ArrayList<>();

    }

    // Returns the max. amount of seats in a row
    public int amountOfSeatsInARow() {

        int mostRowSeats = 0;

        for (SeatRowInstance seatRowInstance : seatRowInstances) {

            int currentRowSeats = seatRowInstance.getAmountOfSeats();

            if (currentRowSeats > mostRowSeats) {
                mostRowSeats = currentRowSeats;

            }

        }

        return mostRowSeats;

    }

    // Returns the total amount of seats
    public int amountOfSeats() {

        int amountOfSeats = 0;

        for (SeatRowInstance seatRowInstance : seatRowInstances) {

            amountOfSeats += seatRowInstance.getAmountOfActualSeats();

        }

        return amountOfSeats;

    }

    // Returns the available amount of seats
    public int amountOfAvailableSeats() {

        int amountOfAvailableSeats = 0;

        for (SeatRowInstance row : seatRowInstances) {

            amountOfAvailableSeats += row.getAmountOfAvailableSeats();

        }

        return amountOfAvailableSeats;

    }

    // SETTERS
    public void setHallInstanceId(int hallInstanceId) {
        this.hallInstanceId = hallInstanceId;
    }
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    public void setSeatRowInstances(ArrayList<SeatRowInstance> seatRowInstances) {
        this.seatRowInstances = seatRowInstances;
    }

    // GETTERS
    public int getHallInstanceId() {
        return hallInstanceId;
    }
    public Hall getHall() {
        return hall;
    }
    public ArrayList<SeatRowInstance> getSeatRowInstances() {
        return this.seatRowInstances;
    }

    @Override
    public String toString() {

        return "HallInstanceId: " + hallInstanceId + "\n" +
                "Amount of Rows: " + seatRowInstances.size() + "\n" +
                "Amount of Seats " + amountOfSeats() + "\n" +
                "Is an instance of HallNr: " + hall.getHallNr();

    }

}
