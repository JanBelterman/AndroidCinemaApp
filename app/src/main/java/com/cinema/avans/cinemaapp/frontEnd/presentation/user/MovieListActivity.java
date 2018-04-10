package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.HallRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatValue;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class MovieListActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Create repository factory and get movies
        repositoryFactory = new RepositoryFactory(getApplicationContext());
        ArrayList<Movie> movies = repositoryFactory.getMovieRepository().getAllMovieWithoutTheirShowings();

        ListView movieListView = findViewById(R.id.movieListView);
        movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        movieListView.setAdapter(movieAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MovieListActivity.this, MovieDetailedActivity.class);
                intent.putExtra("MOVIE", movieAdapter.getItem(i));
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

            }
        });

    }

}
