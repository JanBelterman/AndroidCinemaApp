package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.RemoteShowingRepository;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.logic.callbacks.ShowingsCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailedActivity extends AppCompatActivity implements ShowingsCallback {

    private RemoteShowingRepository showingsRepository;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        // Get movie
        if (getIntent().getExtras() != null) movie = (Movie) getIntent().getExtras().getSerializable("MOVIE");
        showingsRepository = new RemoteShowingRepository(this, this);
        // Display movie
        displayMovie();
        // Setup button to go to Showings
        setupShowingButton();
    }

    // Helper method to display movie information
    private void displayMovie() {
        // Header image
        ImageView movieHeaderImage = findViewById(R.id.daMovieHeaderImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieHeaderImage);
        // Small image
        ImageView movieImage = findViewById(R.id.daMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);
        // Title
        TextView movieTitle = findViewById(R.id.maMovieTitle);
        movieTitle.setText(movie.getTitle());
        // Runtime
        TextView movieRuntime = findViewById(R.id.daMovieDuration);
        movieRuntime.setText("Duration: " + movie.getRuntime() + " min");
        // Rating
        TextView movieRating = findViewById(R.id.daMovieRating);
        movieRating.setText("Rated " + movie.getRating() + " by IMDB");
        // Release date
        TextView releaseDate = findViewById(R.id.daMovieReleaseDate);
        releaseDate.setText(movie.getReleaseDate());
        // Description
        TextView movieDescription = findViewById(R.id.daMovieDescription);
        movieDescription.setText(movie.getDescription());
        // Genre
        TextView genre = findViewById(R.id.daMovieGenre);
        genre.setText(movie.getGenre().getTitle());
        // Director
        TextView director = findViewById(R.id.daMovieDirector);
        director.setText(movie.getDirector());
        // Actors
        TextView actors = findViewById(R.id.daMovieActors);
        actors.setText(movie.getActors());
        // Production
        TextView production = findViewById(R.id.daMovieProduction);
        production.setText(movie.getProduction());
    }

    // Helper method to setup button to go trough to Showings for this movie
    private void setupShowingButton() {
        // Setup listener
        Button showingsButton = findViewById(R.id.daMovieShowingsButton);
        showingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If Showings still have to be added to the Movie
                if (movie.getShowings().size() == 0) {
                    // Start loading indicator
                    startLoader();
                    // Start async task to get Showings for the Movie
                    showingsRepository.getForMovie(movie);
                }
                // If user pressed the back button and Showings already found
                else {
                    Intent intent = new Intent(MovieDetailedActivity.this, ShowingsActivity.class);
                    intent.putExtra("MOVIE", movie);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }
            }
        });
    }

    @Override
    public void showingsFound(ArrayList<Showing> showings) {
        stopLoader();
        movie.setShowings(showings);
        Intent intent = new Intent(MovieDetailedActivity.this, ShowingsActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void startLoader() {
        findViewById(R.id.daLoadingShowingsProgressbar).setVisibility(View.VISIBLE);
    }

    private void stopLoader() {
        findViewById(R.id.daLoadingShowingsProgressbar).setVisibility(View.INVISIBLE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

}
