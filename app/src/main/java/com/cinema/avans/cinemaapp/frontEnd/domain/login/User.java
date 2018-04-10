package com.cinema.avans.cinemaapp.frontEnd.domain.login;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Ticket;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28,March,2018
 */

public class User extends LogIn {

    private ArrayList<Ticket> tickets;

    public User() {
        super();

        this.tickets = new ArrayList<>();

    }


    public int getId() {
        return 1;

    }

    // SETTERS
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    // GETTERS
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    @Override
    public String toString() {

        return "User:" + "\n" +
                "- Username: " + username + "\n" +
                "- Password: " + password;

    }

}
