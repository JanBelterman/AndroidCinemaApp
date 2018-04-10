package com.cinema.avans.cinemaapp.frontEnd.logic.manager;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface HallsRetrievedListener {

    void hallsRetrieved(ArrayList<Hall> halls);

}
