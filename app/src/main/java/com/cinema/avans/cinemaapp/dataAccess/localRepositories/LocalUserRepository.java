package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LocalUserRepository {

    private DatabaseManager databaseManager;

    public LocalUserRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public void createUser(User user) {
        databaseManager.createUser(user);

    }

    public User getUser(String username) {

        // Get User
        User user = databaseManager.getUser(username);
        // Also get all of its tickets
        user.setTickets(new LocalTicketRepository(databaseManager).getTickets(user));
        // Return
        return user;

    }

}
