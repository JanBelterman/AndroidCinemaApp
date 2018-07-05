package com.cinema.avans.cinemaapp.domain.login;

/**
 * Created by JanBelterman on 28,March,2018
 */

public class Manager extends LogIn {

    public Manager() {

        super();

    }

    public int getId() {

        return 2;

    }

    @Override
    public String toString() {

        return "Manager:" + "\n" +
                "- Username: " + username + "\n" +
                "- Password: " + password;

    }

}
