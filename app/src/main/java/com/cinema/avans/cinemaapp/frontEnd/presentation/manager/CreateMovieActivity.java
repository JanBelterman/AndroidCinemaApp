package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.MovieRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.MovieFactory;
import com.squareup.picasso.Picasso;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateMovieActivity extends AppCompatActivity implements NewMovieListener {

    private MovieFactory movieFactory;
    private MovieRepository movieRepository;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie);

        setupActivity();

        setUpButtons();

    }

    private void setupActivity() {

        // Set up movie factory
        movieFactory = new MovieFactory(this);
        // Set up movie repository
        movieRepository = new RepositoryFactory(getApplicationContext()).getMovieRepository();

    }

    private void setUpButtons() {

        // Search button
        Button searchButton = findViewById(R.id.createMovieSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Ask manager to ask async task to get new movie
                EditText movieSearchInput = findViewById(R.id.createMovieTitleInput);
                movieFactory.getNewMovie(movieSearchInput.getText().toString());

            }
        });

        // Save button
        Button saveButton = findViewById(R.id.createMovieSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (movie == null) {

                    // Alert error
                    Toast.makeText(getApplicationContext(), R.string.chooseAMovieFirst, Toast.LENGTH_SHORT).show();

                } else {

                    // Add movie to database
                    movieRepository.createMovie(movie);

                    // Show message and finish activity
                    Toast.makeText(getApplicationContext(), R.string.movieAdded, Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

    }

    // Listener for when async task found a new movie
    @Override
    public void newApiMovie(Movie movie) {

        this.movie = movie;
        displayMovie();

    }

    // Displays movie information
    private void displayMovie() {

        ImageView movieImage = findViewById(R.id.createMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);

        TextView movieTitle = findViewById(R.id.createMovieTitleText);
        movieTitle.setText(movie.getTitle());

    }

}
