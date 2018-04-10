package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class ShowingRepository {

    private DatabaseManager databaseManager;

    public ShowingRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    // Entire Showing object has to be stored that concludes all of the following things:
    // - Showing
    // - Complete HallInstance with its SeatRowInstances and SeatInstances
    public void createShowing(Showing showing) {

        // Log action
        Log.i("ShowingRepository", "Adding showing:" + "\n" + showing);

        // Log step
        Log.i("ShowingRepository", "First adding hall");
        // Add HallInstance with repository (so RowInstances and SeatInstances are properly added
        // The repository also assigns the HallInstance its HallInstanceId, that wat Showing can be created properly
        // Otherwise the Showing would not refer to the correct HallInstance, because HallInstanceId would always be 0
        new HallInstanceRepository(databaseManager).createHallInstance(showing.getHallInstance());

        // Log step
        Log.i("ShowingRepository", "Now adding Showing: " + showing);
        // Create the Showing record itself
        databaseManager.createShowing(showing);

    }

    public ArrayList<Showing> getShowings(Movie movie) {

        // Log action
        Log.i("ShowingRepository", "Asking database all Showings for movie: " + movie);

        // Get Showings
        ArrayList<Showing> showings = databaseManager.getShowings(movie);
        for (Showing showing : showings) {

            // Get HallInstance from repository (that one is complete with RowInstances and SeatInstances
            // Add movie (parameter)
            showing.setHallInstance(new HallInstanceRepository(databaseManager).getHallInstance(showing));
            showing.setMovie(movie);

            Log.i("ShowingRepository", "Complete Showing: " + showing);

        }

        // Return the showings for the given movie
        return showings;

    }

    Showing getShowing(int showingId) {

        // Log action
        Log.i("ShowingRepository", "Asking database for Showing with ShowingId: " + showingId);

        // Get Showing
        Showing showing = databaseManager.getShowing(showingId);
        // Also add HallInstance and Movie
        showing.setHallInstance(new HallInstanceRepository(databaseManager).getHallInstance(showing));
        showing.setMovie(new MovieRepository(databaseManager).getMovie(showing.getMovie().getMovieId()));

        // Return Showing
        return showing;

    }

}
