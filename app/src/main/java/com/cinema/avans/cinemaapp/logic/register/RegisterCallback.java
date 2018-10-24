package com.cinema.avans.cinemaapp.logic.register;

import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 24 October 2018
 */
public interface RegisterCallback {

    void login(User user);
    void falseRegister(String message);

}
