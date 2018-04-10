package com.cinema.avans.cinemaapp.frontEnd.domain.login;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28,March,2018
 */

public abstract class LogIn implements Serializable {

    protected String username;
    String password;

    LogIn() {

        this.username = "";
        this.password = "";

    }

    // SETTERS
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // GETTERS
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String toString() {

        return "UserId: " + username + ", password: " + password;

    }

    public abstract int getId();

}
