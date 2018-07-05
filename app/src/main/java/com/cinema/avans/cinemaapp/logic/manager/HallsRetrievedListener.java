package com.cinema.avans.cinemaapp.logic.manager;

import com.cinema.avans.cinemaapp.domain.cinema.Hall;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface HallsRetrievedListener {

    void hallsRetrieved(ArrayList<Hall> halls);

}
