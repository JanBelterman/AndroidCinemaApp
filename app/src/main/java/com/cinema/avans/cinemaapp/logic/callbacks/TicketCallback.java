package com.cinema.avans.cinemaapp.logic.callbacks;

import com.cinema.avans.cinemaapp.domain.Ticket;

/**
 * Created by JanBelterman on 24 October 2018
 */
public interface TicketCallback {

    void ticketFound(Ticket ticket);
    void error(String message);

}
