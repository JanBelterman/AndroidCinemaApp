package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.ShowingAddedListener;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.ShowingAdder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by JanBelterman on 31 March 2018
 */

// Step 4: Checking and saving
public class CreateShowingConfirmActivity extends AppCompatActivity implements ShowingAddedListener {

    private ShowingAdder showingAdder;

    private Showing showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_confirm);

        setupActivity();

        displayShowing();

        createConfirmButton();

    }

    private void setupActivity() {

        stopLoader();

        Intent intent = getIntent();

        showing = (Showing) intent.getSerializableExtra("SHOWING");

        showingAdder = new ShowingAdder(this, new RepositoryFactory(getApplicationContext()).getShowingRepository());

    }

    private void displayShowing() {

        TextView movieTitle = findViewById(R.id.createShowingConfirmMovieTitle);
        movieTitle.setText(showing.getMovie().getTitle());

        ImageView movieImage = findViewById(R.id.createShowingConfirmMovieImage);
        Picasso.with(getApplicationContext()).load(showing.getMovie().getImageUrl()).into(movieImage);

        TextView hallNr = findViewById(R.id.createShowingConfirmHall);
        hallNr.setText(getString(R.string.hallNrWithSemiColon) + " " + showing.getHallInstance().getHall().getHallNr());

    }

    private void createConfirmButton() {

        Button confirmButton = findViewById(R.id.createShowingConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLoader();

                // Add showing
                showingAdder.execute(showing);

            }
        });

    }

    @Override
    public void showingAdded() {

        stopLoader();

        Toast.makeText(getApplicationContext(), getString(R.string.showingAdded), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CreateShowingConfirmActivity.this, ManagerHubActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    private void stopLoader() {

        ProgressBar loader = findViewById(R.id.createShowingConfirmLoader);
        loader.setVisibility(View.GONE);

    }

    private void startLoader() {

        ProgressBar loader = findViewById(R.id.createShowingConfirmLoader);
        loader.setVisibility(View.VISIBLE);

    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

}
