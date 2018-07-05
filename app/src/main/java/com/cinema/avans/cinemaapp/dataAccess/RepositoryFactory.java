package com.cinema.avans.cinemaapp.dataAccess;

import android.content.Context;

import com.cinema.avans.cinemaapp.data.DatabaseManager;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.HallRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.LogInRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.ManagerRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.MovieRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.ShowingRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.TicketRepository;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.UserRepository;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class RepositoryFactory {

    private DatabaseManager databaseManager;

    public RepositoryFactory(Context context) {

        this.databaseManager = new DatabaseManager(context, "Cinema", null, 0);

    }

    public HallRepository getHallRepository() {
        return new HallRepository(databaseManager);

    }

//    public SeatRowRepository getSeatRowRepository() {
//        return new SeatRowRepository(databaseManager);
//
//    }

//    public SeatRepository getSeatRepository() {
//        return new SeatRepository(databaseManager);
//
//    }

//    public HallInstanceRepository getHallInstanceRepository() {
//        return new HallInstanceRepository(databaseManager);
//
//    }

//    public SeatRowInstanceRepository getSeatRowInstanceRepository() {
//        return new SeatRowInstanceRepository(databaseManager);
//
//    }

    public SeatInstanceRepository getSeatInstanceRepositoryFactory() {
        return new SeatInstanceRepository(databaseManager);

    }

    public MovieRepository getMovieRepository() {
        return new MovieRepository(databaseManager);

    }

    public ShowingRepository getShowingRepository() {
        return new ShowingRepository(databaseManager);

    }

    public TicketRepository getTicketRepository() {
        return new TicketRepository(databaseManager);

    }

    public LogInRepository getLogInRepository() {
        return new LogInRepository(databaseManager);

    }

    public UserRepository getUserRepository() {
        return new UserRepository(databaseManager);

    }

    public ManagerRepository getManagerRepository() {
        return new ManagerRepository(databaseManager);

    }

}
