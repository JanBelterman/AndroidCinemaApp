package com.cinema.avans.cinemaapp.frontEnd.logic.user;

import android.content.Context;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public interface ShowingsListener {

    void showingsFound(ArrayList<Showing> showings);

}
