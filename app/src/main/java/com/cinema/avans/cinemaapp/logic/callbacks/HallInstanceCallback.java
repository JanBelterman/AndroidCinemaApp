package com.cinema.avans.cinemaapp.logic.callbacks;

import com.cinema.avans.cinemaapp.domain.HallInstance;

/**
 * Created by JanBelterman on 24 October 2018
 */
public interface HallInstanceCallback {

    void hallInstanceFound(HallInstance hallInstance);
    void error(String message);

}
