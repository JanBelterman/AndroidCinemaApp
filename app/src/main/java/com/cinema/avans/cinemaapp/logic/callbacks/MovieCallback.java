package com.cinema.avans.cinemaapp.logic.callbacks;

import com.cinema.avans.cinemaapp.domain.Movie;

/**
 * Created by JanBelterman on 04 July 2018
 */
public interface MovieCallback {

    void movieGotten(Movie movie);
    void errorGotten(String error);

}
