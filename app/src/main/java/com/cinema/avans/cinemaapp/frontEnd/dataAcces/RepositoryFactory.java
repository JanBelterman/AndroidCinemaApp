package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import android.content.Context;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.HallRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.LogInRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.ManagerRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.MovieRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.ShowingRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.TicketRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.UserRepository;

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
