package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class ManagerHubActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_hub);

        setupActivity();

        setUpButtons();

    }

    private void setupActivity() {

        this.repositoryFactory = new RepositoryFactory(getApplicationContext());

    }

    private void setUpButtons() {

        createAddMovieButton();
        createAddHallButton();
        createAddShowingButton();

    }

    private void createAddMovieButton() {

        Button movieButton = findViewById(R.id.mHubCreateMovieButton);
        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerHubActivity.this, CreateMovieActivity.class);
                startActivity(intent);

            }
        });

    }

    private void createAddHallButton() {

        Button hallButton = findViewById(R.id.mHubCreateHallButton);
        hallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerHubActivity.this, CreateHallActivity.class);
                startActivity(intent);

            }
        });

    }

    private void createAddShowingButton() {

        Button showingButton = findViewById(R.id.mHubCreateShowingButton);
        showingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerHubActivity.this, CreateShowingSelectMovieActivity.class);
                intent.putExtra("MOVIES", repositoryFactory.getMovieRepository().getAllMovieWithoutTheirShowings());
                startActivity(intent);

            }
        });

    }

}
