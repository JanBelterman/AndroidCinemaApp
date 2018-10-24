package com.cinema.avans.cinemaapp.dataAccess.factories;

import android.content.Context;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.HallRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalMovieRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.ShowingRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.TicketRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.UserRepository;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LocalRepositoryFactory {

    private DatabaseManager databaseManager;

    public LocalRepositoryFactory(Context context) {
        this.databaseManager = new DatabaseManager(context, "Cinema", null, 0);
    }

    public HallRepository getHallRepository() {
        return new HallRepository(databaseManager);

    }

    public SeatInstanceRepository getSeatInstanceRepositoryFactory() {
        return new SeatInstanceRepository(databaseManager);

    }

    public LocalMovieRepository getMovieRepository() {
        return new LocalMovieRepository(databaseManager);

    }

    public ShowingRepository getShowingRepository() {
        return new ShowingRepository(databaseManager);

    }

    public TicketRepository getTicketRepository() {
        return new TicketRepository(databaseManager);

    }

    public UserRepository getUserRepository() {
        return new UserRepository(databaseManager);

    }

}
