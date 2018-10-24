package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.factories.LocalRepositoryFactory;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.logic.additional.ShowingsGetter;
import com.cinema.avans.cinemaapp.logic.additional.ShowingsListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailedActivity extends AppCompatActivity implements ShowingsListener {

    private Movie movie;
    private User user;

    private ShowingsGetter showingsGetter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);

        setupActivity();

        // Display movie
        displayMovie();

        // Setup button to go trough to Showings
        setupShowingButton();

    }

    private void setupActivity() {

        // Get movie
        movie = (Movie) getIntent().getExtras().getSerializable("MOVIE");
        user = (User) getIntent().getSerializableExtra("USER");

        Log.i("MovieDetailedActivity", "User gotten: " + user);

        // Create ShowingsGetter
        showingsGetter = new ShowingsGetter(new LocalRepositoryFactory(getApplicationContext()).getShowingRepository(), this);

        // Progressbar gone
        progressBar = findViewById(R.id.daLoadingShowingsProgressbar);
        stopLoader();

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
        movieRuntime.setText(String.valueOf(movie.getRuntime()));

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
                    showingsGetter.execute(movie);

                }
                // If user pressed the back button and no Showings have to be found
                else {

                    // Start the intent and pass the movie trough
                    Intent intent = new Intent(MovieDetailedActivity.this, ShowingsActivity.class);
                    intent.putExtra("MOVIE", movie);
                    intent.putExtra("USER", user);
                    stopLoader();
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                }

            }
        });

    }

    // Interface method to continue when Showings have been found
    public void showingsFound(ArrayList<Showing> showings) {

        // Add the Showings to the Movie
        movie.setShowings(showings);

        // Stop the loader
        stopLoader();

        // Start the intent and pass the movie trough
        Intent intent = new Intent(MovieDetailedActivity.this, ShowingsActivity.class);
        intent.putExtra("MOVIE", movie);
        intent.putExtra("USER", user);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

    }

    // Helper methods to start and stop loader
    private void startLoader() {
        progressBar.setVisibility(View.VISIBLE);

    }
    private void stopLoader() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

}
