package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.LogIn;

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

        Log.i("LogInRepository", "Asking database for user and manager for a given username");

        // First look for user
        Log.i("LogInRepository", "Looking for user");
        login = databaseManager.getUser(username);

        if (login != null) {
            Log.i("LogInRepository", "User found so filling user with its tickets");
            login = new UserRepository(databaseManager).getUser(login.getUsername());
            return login;

        }

        // Than look for manager
        Log.i("LogInRepository", "No user found, now looking for manager");
        login = databaseManager.getManager(username);

        if (login != null) {
            Log.i("LogInRepository", "Manager found");
            return login;

        }

        Log.i("LogInRepository", "Also no manager found");

        return null;

    }

}
