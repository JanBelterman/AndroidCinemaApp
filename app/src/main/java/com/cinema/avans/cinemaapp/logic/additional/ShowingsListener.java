package com.cinema.avans.cinemaapp.logic.additional;

import com.cinema.avans.cinemaapp.domain.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface ShowingsListener {

    void showingsFound(ArrayList<Showing> showings);

}
