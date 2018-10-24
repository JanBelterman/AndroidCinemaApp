package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Ticket implements Serializable {

    private int ticketId;
    private Showing showing;
    private SeatInstance seatInstance;
    private User user;

    public Ticket() {

        this.ticketId = 0;
        this.showing = new Showing();
        this.seatInstance = new SeatInstance();

    }

    // SETTERS
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public void setShowing(Showing showing) {
        this.showing = showing;
    }
    public void setSeatInstance(SeatInstance seatInstance) {
        this.seatInstance = seatInstance;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // GETTERS
    public int getTicketId() {
        return ticketId;
    }
    public Showing getShowing() {
        return showing;
    }
    public SeatInstance getSeatInstance() {
        return seatInstance;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {

        return "TicketId: " + ticketId + "\n"
                + "ShowingId: " + showing.getID() + "\n"
                + "SeatInstanceId: " + seatInstance.getID();

    }

}
