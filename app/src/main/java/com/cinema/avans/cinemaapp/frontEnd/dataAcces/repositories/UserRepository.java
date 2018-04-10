package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class UserRepository {

    private DatabaseManager databaseManager;

    public UserRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public void createUser(User user) {

        Log.i("UserRepository", "Creating user:" + "\n" + user);
        databaseManager.createUser(user);

    }

    public User getUser(String username) {

        Log.i("UserRepository", "Getting user for username: " + username);

        // Get User
        User user = databaseManager.getUser(username);
        // Also get all of its tickets
        user.setTickets(new TicketRepository(databaseManager).getTickets(user));
        // Return
        return user;

    }

}
