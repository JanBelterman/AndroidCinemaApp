package com.cinema.avans.cinemaapp.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.logic.manager.HallRetriever;
import com.cinema.avans.cinemaapp.logic.manager.HallsRetrievedListener;
import com.cinema.avans.cinemaapp.presentation.user.MovieAdapter;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

// Step 1: Selecting movie
public class CreateShowingSelectMovieActivity extends AppCompatActivity implements HallsRetrievedListener {

    private Showing showing;
    private ArrayList<Movie> movies;
    private ArrayList<Hall> halls;

    private HallRetriever hallRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_select_movie);

        setupActivity();

        // Display movies
        final MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        ListView movieListView = findViewById(R.id.createShowingSelectMovieListView);
        movieListView.setAdapter(movieAdapter);

        // Set click listener
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Add selected movie to the showing
                showing.setMovie(movieAdapter.getItem(i));

                if (halls.size() != 0) {

                    Intent intent = new Intent(CreateShowingSelectMovieActivity.this, CreateShowingSelectHallActivity.class);
                    intent.putExtra("SHOWING", showing);
                    intent.putExtra("HALLS", halls);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                } else {

                    startLoader();

                    // Get halls with async task
                    hallRetriever.execute();

                }

            }
        });

    }

    private void setupActivity() {

        Intent intent = getIntent();

        stopLoader();

        // Create showing
        showing = new Showing();
        halls = new ArrayList<>();

        // Get movies
        movies = (ArrayList<Movie>) intent.getSerializableExtra("MOVIES");

        hallRetriever = new HallRetriever(this, new RepositoryFactory(getApplicationContext()).getHallRepository());

    }

    @Override
    public void hallsRetrieved(ArrayList<Hall> halls) {

        this.halls = halls;

        // Send selected movie back to create showing activity
        Intent intent = new Intent(CreateShowingSelectMovieActivity.this, CreateShowingSelectHallActivity.class);
        intent.putExtra("SHOWING", showing);
        intent.putExtra("HALLS", halls);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        stopLoader();

    }

    private void startLoader() {

        ProgressBar progressBar = findViewById(R.id.createShowingSelectMovieProgress);
        progressBar.setVisibility(View.VISIBLE);

    }

    private void stopLoader() {

        ProgressBar progressBar = findViewById(R.id.createShowingSelectMovieProgress);
        progressBar.setVisibility(View.GONE);

    }

}
