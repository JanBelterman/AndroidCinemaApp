package com.cinema.avans.cinemaapp.logic.manager;

import com.cinema.avans.cinemaapp.dataAccess.NewMovieListener;
import com.cinema.avans.cinemaapp.dataAccess.connections.MovieApiManager;
import com.cinema.avans.cinemaapp.domain.cinema.Movie;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class MovieFactory implements NewMovieListener {

    private NewMovieListener newMovieListener;

    public MovieFactory(NewMovieListener newMovieListener) {
        this.newMovieListener = newMovieListener;

    }

    public void getNewMovie(String title) {

        MovieApiManager movieApiManager = new MovieApiManager(this);
        movieApiManager.execute("http://www.omdbapi.com/?apikey=556fd57f&plot=full&t=" + title);

    }

    public void newApiMovie(Movie movie) {

        newMovieListener.newApiMovie(movie);

    }

}
