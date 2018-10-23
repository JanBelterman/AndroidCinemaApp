package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Seat implements Serializable {

    private int seatId; // Done
    private SeatRow seatRow; // Done
    private int seatNr; // Done
    private SeatValue seatValue; // Done

    public Seat() {

        this.seatId = 0;
        this.seatRow = new SeatRow();
        this.seatNr = 0;
        this.seatValue = SeatValue.OK;

    }

    // SETTERS
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public void setSeatRow(SeatRow seatRow) {
        this.seatRow = seatRow;
    }
    public int getSeatNr() {
        return seatNr;
    }
    public void setSeatNr(int seatNr) {
        this.seatNr = seatNr;
    }
    public void setSeatValue(SeatValue seatValue) {
        this.seatValue = seatValue;
    }
    // ALTERED SETTERS
    public void setValue(int valueInt) {

        if (valueInt == 5) {
            seatValue = SeatValue.PERFECT;

        } else if (valueInt == 4) {
            seatValue = SeatValue.GOOD;

        } else if (valueInt == 3) {
            seatValue = SeatValue.OK;

        } else if (valueInt == 2) {
            seatValue = SeatValue.MODERATE;

        } else if (valueInt == 1) {
            seatValue = SeatValue.BAD;

        }

    }

    // GETTERS
    public int getSeatId() {
        return seatId;
    }
    public SeatRow getSeatRow() {
        return seatRow;
    }
//    public SeatValue getSeatValue() {
//        return seatValue;
//    }
    // ALTERED GETTERS
    public int getSeatValueInt() {

        if (seatValue == SeatValue.PERFECT) {
            return 5;

        } else if (seatValue == SeatValue.GOOD) {
            return 4;

        } else if (seatValue == SeatValue.OK) {
            return 3;

        } else if (seatValue == SeatValue.MODERATE) {
            return 2;

        } else if (seatValue == SeatValue.BAD) {
            return 1;

        }

        return 0;

    }

    @Override
    public String toString() {

        return "SeatId:" + seatId + "\n" +
                "Seat in hall: " + seatRow.getHall() + "\n" +
                "Seat in SeatRow: " + seatRow + "\n" +
                "SeatNr: " + seatNr + "\n" +
                "SeatValue: " + seatValue;

    }

}
