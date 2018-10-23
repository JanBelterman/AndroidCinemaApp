package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28,March,2018
 */

public class User implements Serializable {

    private String token;
    private String username;
    private String password;
    private ArrayList<Ticket> tickets;

    public User() {

        this.token = "";
        this.username = "";
        this.password = "";
        this.tickets = new ArrayList<>();

    }

    // SETTERS
    public void setToken(String token) {
        this.token = token;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    // GETTERS
    public String getToken() {
        return this.token;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
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
