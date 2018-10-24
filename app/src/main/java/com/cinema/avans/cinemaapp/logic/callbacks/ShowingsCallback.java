package com.cinema.avans.cinemaapp.logic.callbacks;

import com.cinema.avans.cinemaapp.domain.Showing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface ShowingsCallback {

    void showingsFound(ArrayList<Showing> showings);
    void error(String message);

}
