package com.cinema.avans.cinemaapp.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.domain.cinema.Ticket;
import com.cinema.avans.cinemaapp.domain.login.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 03 April 2018
 */

public class PayActivity extends AppCompatActivity implements TicketManagerFinishedListener {

    private TicketBoughtManager ticketBoughtManager;

    private ArrayList<SeatInstance> seatInstancesForUser;
    private User user;
    private Showing showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        user = (User) getIntent().getSerializableExtra("USER");

        Log.i("PaymentActivity", "User gotten: " + user);

        stopLoader();

        seatInstancesForUser = (ArrayList<SeatInstance>) getIntent().getSerializableExtra("SELECTED_SEATS");
        showing = (Showing) getIntent().getSerializableExtra("SHOWING");

        ticketBoughtManager = new TicketBoughtManager(this,
                new RepositoryFactory(getApplicationContext()),
                seatInstancesForUser,
                user);

        Button payButton = findViewById(R.id.payPayButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ticket[] tickets = new Ticket[seatInstancesForUser.size()];

                int i = 0;
                for (SeatInstance seatInstance : seatInstancesForUser) {

                    tickets[i] = createTicket(i);
                    Log.i("PayActivity", "Created ticket: " + tickets[i]);
                    i++;

                }

                startLoader();

                ticketBoughtManager.execute(tickets);

            }
        });

    }

    public Ticket createTicket(int seatInstanceIndex) {

        // Create Ticket
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowing(showing);
        ticket.setSeatInstance(seatInstancesForUser.get(seatInstanceIndex));
        // Return Ticket
        return ticket;

    }

    @Override
    public void ticketManagerFinished(User user) {

        stopLoader();

        Intent intent = new Intent(PayActivity.this, UserMenuActivity.class);
        intent.putExtra("USER", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

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
