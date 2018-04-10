package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.os.AsyncTask;
import android.util.Log;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Ticket;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 05 April 2018
 */

public class TicketBoughtManager extends AsyncTask<Ticket, Void, User> {

    private TicketManagerFinishedListener ticketManagerFinishedListener;
    private RepositoryFactory repositoryFactory;

    private ArrayList<SeatInstance> seatInstances;
    private User user;

    public TicketBoughtManager(TicketManagerFinishedListener ticketManagerFinishedListener,
                               RepositoryFactory repositoryFactory, ArrayList<SeatInstance> seatInstances, User user) {

        this.ticketManagerFinishedListener = ticketManagerFinishedListener;
        this.repositoryFactory = repositoryFactory;
        this.seatInstances = seatInstances;
        this.user = user;

    }

    @Override
    protected User doInBackground(Ticket... tickets) {

        Log.i("TicketBoughtManager", "First ticket: " + tickets[0]);

        // Create ticket
        for (Ticket ticket : tickets) {
            repositoryFactory.getTicketRepository().createTicket(ticket);
        }

        // Update Seats to RESERVED in database
        SeatInstanceRepository seatInstanceRepository = repositoryFactory.getSeatInstanceRepositoryFactory();
        for (SeatInstance seatInstance : seatInstances) {
            seatInstance.setStatus(SeatStatus.RESERVED);
        }
        seatInstanceRepository.updateSeats(seatInstances);

        //User from mainAct
        return repositoryFactory.getUserRepository().getUser(user.getUsername());

    }

    @Override
    protected void onPostExecute(User user) {

        ticketManagerFinishedListener.ticketManagerFinished(user);

    }

}
