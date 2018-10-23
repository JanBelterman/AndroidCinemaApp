package com.cinema.avans.cinemaapp.dataAccess.callbacks;

import com.cinema.avans.cinemaapp.domain.Movie;

/**
 * Created by JanBelterman on 04 July 2018
 */
public interface MovieGottenCallback {

    void movieGotten(Movie movie);
    void showError(String error);

}
