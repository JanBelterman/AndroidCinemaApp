package com.cinema.avans.cinemaapp.frontEnd.logic.logIn;

import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public interface LogInActivity {

    void managerLogIn(Manager manager);
    void userLogIn(User user);

}
