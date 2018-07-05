package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.login.Manager;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class ManagerRepository {

    DatabaseManager databaseManager;

    public ManagerRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public void createManager(Manager manager) {

        databaseManager.createManager(manager);

    }

}
