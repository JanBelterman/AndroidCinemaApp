package com.cinema.avans.cinemaapp.frontEnd.logic.user;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatSelector implements Serializable {

    private Showing showing;
    private HallInstance hallInstance;
    private ArrayList<SeatInstance> selectedSeatInstances;
    private int amountOfSeatsUserWants;

    public SeatSelector(Showing showing,
                        HallInstance hallInstance
                        ,int amountOfSeatsUserWants) {
        this.showing = showing;
        this.hallInstance = hallInstance;
        this.amountOfSeatsUserWants = amountOfSeatsUserWants;
        this.selectedSeatInstances = new ArrayList<>();

    }

    public HallInstance getHallInstance() {
        return hallInstance;

    }

    // Because the SeatSelector has to be bottom up like a normal cinema hall
    public HallInstance getHallInstanceToShow() {

        Collections.reverse(hallInstance.getSeatRowInstances());

        return hallInstance;

    }

    // Method called by UI when user clicks a seatInstance
    public void seatClicked(SeatInstance seatInstance) {

        // Remove seatInstance is user already clicked it
        if (selectedSeatInstances.contains(seatInstance)) {
            selectedSeatInstances.remove(seatInstance);
            seatInstance.setStatus(SeatStatus.AVAILABLE);

        // Add seatInstance (only if the seatInstance is available)
        } else if (seatInstance.getStatus() == SeatStatus.AVAILABLE) {
            selectedSeatInstances.add(seatInstance);
            seatInstance.setStatus(SeatStatus.SELECTED);

        }
        // If to much seats are being selected:
        // - remove the first that user selected
        // - also set the status back to available
        if (selectedSeatInstances.size() > amountOfSeatsUserWants) {
            selectedSeatInstances.get(0).setStatus(SeatStatus.AVAILABLE);
            selectedSeatInstances.remove(0);
        }

    }

    public boolean isValid() {
        return selectedSeatInstances.size() >= amountOfSeatsUserWants;

    }

    public int getAmount() {
        return amountOfSeatsUserWants;

    }

    public void setAmount(int amount) {

        this.amountOfSeatsUserWants = amount;

        for (SeatInstance seatInstance : selectedSeatInstances) {
            seatInstance.setStatus(SeatStatus.AVAILABLE);

        }

        selectedSeatInstances.clear();

    }

    public ArrayList<SeatInstance> getSelectedSeats() {
        return selectedSeatInstances;

    }

    public Showing getShowing() {
        return showing;

    }

}
