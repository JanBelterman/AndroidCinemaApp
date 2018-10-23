package com.cinema.avans.cinemaapp.dataAccess;

import com.cinema.avans.cinemaapp.domain.cinema.Movie;

/**
 * Created by JanBelterman on 29 March 2018
 */

public interface NewMovieListener {

    void newApiMovie(Movie movie);

}
