package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.Movie;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class LocalMovieRepository {

    private DatabaseManager databaseManager;

    public LocalMovieRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public ArrayList<Movie> getAllMovieWithoutTheirShowings() {

        // Get movies
        return databaseManager.getAllMovies();

    }

    Movie getMovie(int movieId) {
        return databaseManager.getMovie(movieId);

    }

}
