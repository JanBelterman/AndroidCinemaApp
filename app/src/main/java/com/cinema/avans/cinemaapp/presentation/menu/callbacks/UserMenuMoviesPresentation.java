package com.cinema.avans.cinemaapp.presentation.menu.callbacks;

import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 04 July 2018
 */
public interface UserMenuMoviesPresentation {

    void startDetailedMovieActivity(Movie movie);
    void showError(String error);
    void movieAdded();

}
