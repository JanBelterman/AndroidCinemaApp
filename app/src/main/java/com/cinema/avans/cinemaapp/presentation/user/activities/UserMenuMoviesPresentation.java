package com.cinema.avans.cinemaapp.presentation.user.activities;

import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 04 July 2018
 */
public interface UserMenuMoviesPresentation {

    void startDetailedMovieActivity(User user, Movie movie);
    void showError(String error);
    void movieAdded();

}
