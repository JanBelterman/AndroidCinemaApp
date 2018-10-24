package com.cinema.avans.cinemaapp.logic.additional;

import android.os.AsyncTask;
import android.util.Log;

import com.cinema.avans.cinemaapp.dataAccess.factories.LocalRepositoryFactory;
import com.cinema.avans.cinemaapp.dataAccess.localRepositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.SeatStatus;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.domain.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 05 April 2018
 */

public class TicketBoughtManager extends AsyncTask<Ticket, Void, User> {

    private TicketManagerFinishedListener ticketManagerFinishedListener;
    private LocalRepositoryFactory localRepositoryFactory;

    private ArrayList<SeatInstance> seatInstances;
    private User user;

    public TicketBoughtManager(TicketManagerFinishedListener ticketManagerFinishedListener,
                               LocalRepositoryFactory localRepositoryFactory, ArrayList<SeatInstance> seatInstances, User user) {

        this.ticketManagerFinishedListener = ticketManagerFinishedListener;
        this.localRepositoryFactory = localRepositoryFactory;
        this.seatInstances = seatInstances;
        this.user = user;

    }

    @Override
    protected User doInBackground(Ticket... tickets) {

        Log.i("TicketBoughtManager", "First ticket: " + tickets[0]);

        // Create ticket
        for (Ticket ticket : tickets) {
            localRepositoryFactory.getTicketRepository().createTicket(ticket);
        }

        // Update Seats to RESERVED in database
        SeatInstanceRepository seatInstanceRepository = localRepositoryFactory.getSeatInstanceRepositoryFactory();
        for (SeatInstance seatInstance : seatInstances) {
            seatInstance.setStatus(SeatStatus.RESERVED);
        }
        seatInstanceRepository.updateSeats(seatInstances);

        //User from mainAct
        return localRepositoryFactory.getUserRepository().getUser(user.getUsername());

    }

    @Override
    protected void onPostExecute(User user) {

        ticketManagerFinishedListener.ticketManagerFinished(user);

    }

}
