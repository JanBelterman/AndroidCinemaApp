package com.cinema.avans.cinemaapp.logic.user;

import android.app.Activity;

import com.cinema.avans.cinemaapp.dataAccess.callbacks.MovieGottenCallback;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.MovieRepository;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.presentation.user.activities.UserMenuMoviesPresentation;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 July 2018
 */
public class UserMenuMoviesManager implements MovieGottenCallback {

    // Presentation
    private UserMenuMoviesPresentation userMenuMoviesPresentation;

    // Data access
    private MovieRepository movieRepository;

    // Domain
    private ArrayList<Movie> movies;
    private User user;

    // Constructor
    public UserMenuMoviesManager(Activity activity, UserMenuMoviesPresentation userMenuMoviesPresentation) {

        this.userMenuMoviesPresentation = userMenuMoviesPresentation;
        movieRepository = new MovieRepository(this, activity);
        movies = new ArrayList<>();

        movieRepository.getMovies();

    }

    // Called by presentation: calls the presentation to start a detailed activity for a movie
    public void movieClicked(int index) {

        Movie movie = movies.get(index);
        userMenuMoviesPresentation.startDetailedMovieActivity(user, movie);

    }

    // Called by presentation: sets the user
    public void setUser(User user) {
        this.user = user;
    }

    // Called by presentation: adds the movies to the adapter
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    // Called by data access: adds a new movie to the gridView
    @Override
    public void movieGotten(Movie movie) {

        movies.add(movie);
        userMenuMoviesPresentation.movieAdded();

    }

    // Called by data access: calls the presentation to show an error
    @Override
    public void showError(String error) {

        userMenuMoviesPresentation.showError(error);

    }

}
