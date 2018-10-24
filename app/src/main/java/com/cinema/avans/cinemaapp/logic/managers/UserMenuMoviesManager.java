package com.cinema.avans.cinemaapp.logic.additional;

import android.app.Activity;

import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.dataAccess.callbacks.MovieGottenCallback;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.RemoteMovieRepository;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.presentation.menu.callbacks.UserMenuMoviesPresentation;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 July 2018
 */
public class UserMenuMoviesManager implements MovieGottenCallback {

    // Presentation callback
    private UserMenuMoviesPresentation userMenuMoviesPresentation;
    // Data access
    private RemoteMovieRepository remoteMovieRepository;
    // Domain
    private ArrayList<Movie> movies;

    // Constructor
    public UserMenuMoviesManager(Activity activity, UserMenuMoviesPresentation userMenuMoviesPresentation) {
        this.userMenuMoviesPresentation = userMenuMoviesPresentation;
        remoteMovieRepository = new RemoteMovieRepository(this, activity);
        movies = new ArrayList<>();
    }

    public void loadMovies() {
        remoteMovieRepository.getMovies();
    }

    // Called by presentation -> calls the presentation to start a detailed activity for a movie
    public void movieClicked(int index) {
        Movie movie = movies.get(index);
        userMenuMoviesPresentation.startDetailedMovieActivity(movie);
    }

    // Called by presentation
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    // Called by data access -> calls presentation to show movie
    @Override
    public void movieGotten(Movie movie) {
        movies.add(movie);
        userMenuMoviesPresentation.movieAdded();
    }

    // Called by data access -> calls the presentation to show an error
    @Override
    public void showError(String error) {
        userMenuMoviesPresentation.showError(error);
    }

}
