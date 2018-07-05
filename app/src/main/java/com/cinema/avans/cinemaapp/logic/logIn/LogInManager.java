package com.cinema.avans.cinemaapp.logic.logIn;

import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.login.LogIn;
import com.cinema.avans.cinemaapp.domain.login.Manager;
import com.cinema.avans.cinemaapp.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LogInManager {

    private RepositoryFactory repositoryFactory;
    private LogInActivity logInActivity;

    public LogInManager(LogInActivity logInActivity, RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        this.logInActivity = logInActivity;

    }

    public boolean logIn(String userName, String password) {

        LogIn logIn = repositoryFactory.getLogInRepository().getLogIn(userName);

        if (logIn == null) {
            return false;

        }

        if (logIn.getId() == 2) {

            if (logIn.getUsername().equals(userName) && logIn.getPassword().equals(password)) {
                logInActivity.managerLogIn((Manager) logIn);
                return true;
            }

        } if (logIn.getId() == 1) {

            if (logIn.getUsername().equals(userName) && logIn.getPassword().equals(password)) {
                logInActivity.userLogIn((User) logIn);
                return true;
            }

        }

        return false;

    }

}
