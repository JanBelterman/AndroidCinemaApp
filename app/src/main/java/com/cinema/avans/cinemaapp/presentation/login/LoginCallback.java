package com.cinema.avans.cinemaapp.presentation.login;

import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public interface LoginCallback {

    void login(User user);
    void falseLogin(String message);

}
