package com.cinema.avans.cinemaapp.logic.user;

import com.cinema.avans.cinemaapp.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface ShowingsListener {

    void showingsFound(ArrayList<Showing> showings);

}
