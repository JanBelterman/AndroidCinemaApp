package com.cinema.avans.cinemaapp.presentation.menu.screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.RemoteTicketRepository;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.logic.callbacks.TicketCallback;
import com.cinema.avans.cinemaapp.presentation.menu.adapters.TicketListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMenuTicketsFragment extends Fragment implements TicketCallback {

    private TicketListAdapter ticketListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_tickets, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView ticketListView = view.findViewById(R.id.userHubUserTicketsListView);
        ticketListAdapter = new TicketListAdapter(getContext(), Session.user.getTickets());
        ticketListView.setAdapter(ticketListAdapter);
        ticketListAdapter.clear();
        start();
        new RemoteTicketRepository(getActivity(), this).getAllForUser();
    }

    @Override
    public void ticketFound(Ticket ticket) {
        stop();
        ticketListAdapter.add(ticket);
        ticketListAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(String message) {
        stop();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void start() {
        getActivity().findViewById(R.id.ticketsProgressBar).setVisibility(View.VISIBLE);
    }

    private void stop() {
        getActivity().findViewById(R.id.ticketsProgressBar).setVisibility(View.INVISIBLE);
    }

}
