package com.cinema.avans.cinemaapp.presentation.user;

import com.cinema.avans.cinemaapp.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.domain.login.User;

/**
 * Created by JanBelterman on 04 July 2018
 */
public interface UserMenuMoviesPresentation {

    void startDetailedMovieActivity(User user, Movie movie);
    void showError(String error);
    void movieAdded();

}
