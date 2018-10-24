package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;

public class SeatInstance implements Serializable {

    private int ID; // Done
    private Seat seat; // Done
    private SeatRowInstance seatRowInstance; // Done
    private SeatStatus status; // Done

    public SeatInstance() {

        this.ID = 0;
        this.seat = new Seat();
        this.seatRowInstance = new SeatRowInstance();
        this.status = SeatStatus.AVAILABLE;

    }

    // SETTERS
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void setSeatRowInstance(SeatRowInstance seatRowInstance) {
        this.seatRowInstance = seatRowInstance;
    }
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    // ALTERED SETTERS
    public void setStatus(int statusInt) {

        if (statusInt == 1) {
            status = SeatStatus.AVAILABLE;

        } else if (statusInt == 2) {
            status = SeatStatus.RESERVED;

        } else if (statusInt == 3) {
            status = SeatStatus.GAP;

        } else {
            status = SeatStatus.SELECTED;

        }

    }

    // GETTERS
    public int getID() {
        return this.ID;
    }
    public Seat getSeat() {
        return seat;
    }
    public SeatRowInstance getSeatRowInstance() {
        return seatRowInstance;
    }
    public SeatStatus getStatus() {
        return status;
    }
    // ALTERED GETTERS
    public int getStatusInt() {

        if (status == SeatStatus.AVAILABLE) {
            return 1;

        } else if (status == SeatStatus.RESERVED) {
            return 2;

        } else if (status == SeatStatus.GAP) {
            return 3;

        }

        return 0;

    }

    @Override
    public String toString() {

        return "SeatInstanceId: " + ID + "\n" +
                "Is an instance of: " + seat + "\n" +
                "Withing SeatRowInstance: " + seatRowInstance + "\n" +
                "Status: " + status;

    }

}
