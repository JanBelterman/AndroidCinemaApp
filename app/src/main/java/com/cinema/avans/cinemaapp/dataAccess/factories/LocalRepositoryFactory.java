package com.cinema.avans.cinemaapp.dataAccess.factories;

import android.content.Context;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalHallRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalMovieRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalSeatInstanceRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalShowingRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalTicketRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LocalUserRepository;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LocalRepositoryFactory {

    private DatabaseManager databaseManager;

    public LocalRepositoryFactory(Context context) {
        this.databaseManager = new DatabaseManager(context, "Cinema", null, 0);
    }

    public LocalHallRepository getHallRepository() {
        return new LocalHallRepository(databaseManager);

    }

    public LocalSeatInstanceRepository getSeatInstanceRepositoryFactory() {
        return new LocalSeatInstanceRepository(databaseManager);

    }

    public LocalMovieRepository getMovieRepository() {
        return new LocalMovieRepository(databaseManager);

    }

    public LocalShowingRepository getShowingRepository() {
        return new LocalShowingRepository(databaseManager);

    }

    public LocalTicketRepository getTicketRepository() {
        return new LocalTicketRepository(databaseManager);

    }

    public LocalUserRepository getUserRepository() {
        return new LocalUserRepository(databaseManager);

    }

}
