package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.login.LogIn;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LogInRepository {

    private DatabaseManager databaseManager;

    public LogInRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public LogIn getLogIn(String username) {

        LogIn login;

        login = databaseManager.getUser(username);

        if (login != null) {
            login = new UserRepository(databaseManager).getUser(login.getUsername());
            return login;

        }

        // Than look for manager
        login = databaseManager.getManager(username);

        if (login != null) {
            return login;

        }

        return null;

    }

}
