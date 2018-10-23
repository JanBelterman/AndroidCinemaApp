package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.cinema.Ticket;
import com.cinema.avans.cinemaapp.domain.login.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class TicketRepository {

    private DatabaseManager databaseManager;

    public TicketRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createTicket(Ticket ticket) {
        databaseManager.createTicket(ticket);

    }

    ArrayList<Ticket> getTickets(User user) {

        // Get tickets from database
        ArrayList<Ticket> tickets = databaseManager.getTickets(user.getUsername());

        // Also add user and movie
        for (Ticket ticket : tickets) {
            ticket.setUser(user);
            ticket.setShowing(new ShowingRepository(databaseManager).getShowing(ticket.getShowing().getShowingId()));

        }

        // Return tickets
        return tickets;

    }

}
