package com.cinema.avans.cinemaapp.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class HallInstance implements Serializable {

    // HallInstance attributes
    private int ID;
    // Parent Hall
    private Hall hall;
    // SeatRowInstances of this HallInstance
    private ArrayList<SeatRowInstance> seatRowInstances;

    // Constructor
    public HallInstance() {

        this.ID = 0;
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
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    public void setSeatRowInstances(ArrayList<SeatRowInstance> seatRowInstances) {
        this.seatRowInstances = seatRowInstances;
    }

    // GETTERS
    public int getID() {
        return ID;
    }
    public Hall getHall() {
        return hall;
    }
    public ArrayList<SeatRowInstance> getSeatRowInstances() {
        return this.seatRowInstances;
    }

    @Override
    public String toString() {

        return "ID: " + ID + "\n" +
                "Is an instance of hallNr: " + hall.getHallNr() + "\n" +
                "Amount of Rows: " + seatRowInstances.size() + "\n" +
                "Amount of Seats " + amountOfSeats();

    }

}
