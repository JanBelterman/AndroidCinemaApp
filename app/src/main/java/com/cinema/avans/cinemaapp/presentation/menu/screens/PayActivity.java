package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.dataAccess.factories.LocalRepositoryFactory;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.RemoteTicketRepository;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.logic.callbacks.PaymentCallback;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 03 April 2018
 */

public class PayActivity extends AppCompatActivity implements PaymentCallback {

    private RemoteTicketRepository ticketRepository;

    private ArrayList<SeatInstance> seatInstancesForUser;
    private Showing showing;

    // TODO counter till all tickets have responded
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        seatInstancesForUser = (ArrayList<SeatInstance>) getIntent().getSerializableExtra("SELECTED_SEATS");
        showing = (Showing) getIntent().getSerializableExtra("SHOWING");
        ticketRepository = new RemoteTicketRepository(this, this);
        createPayButton();
    }

    private void createPayButton() {
        Button payButton = findViewById(R.id.payPayButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Ticket> tickets = new ArrayList<>();
                for (SeatInstance seatInstance : seatInstancesForUser) {
                    Ticket ticket = new Ticket();
                    ticket.setShowing(showing);
                    ticket.setSeatInstance(seatInstance);
                    tickets.add(ticket);
                }
                startLoader();
                ticketRepository.addAll(tickets);
            }
        });
    }

    @Override
    public void success() {
        stopLoader();
        Intent intent = new Intent(PayActivity.this, UserMenuActivity.class);
        Toast.makeText(getApplicationContext(), "Tickets payed", Toast.LENGTH_LONG).show();
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    public void error(String message) {
        stopLoader();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void startLoader() {
        ProgressBar progressBar = findViewById(R.id.payProgress);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoader() {
        ProgressBar progressBar = findViewById(R.id.payProgress);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

}
