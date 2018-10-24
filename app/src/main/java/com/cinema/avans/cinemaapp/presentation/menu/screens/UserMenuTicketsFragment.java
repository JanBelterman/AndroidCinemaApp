package com.cinema.avans.cinemaapp.presentation.menu.screens;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.presentation.menu.adapters.TicketListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMenuTicketsFragment extends Fragment {

    private User user;
    private TicketListAdapter ticketListAdapter;
    private ListView ticketListView;

    public UserMenuTicketsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.user = Session.user;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_hub_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ticketListView = view.findViewById(R.id.userHubUserTicketsListView);
        ticketListAdapter = new TicketListAdapter(getContext(), user.getTickets());
        ticketListView.setAdapter(ticketListAdapter);

    }


}
