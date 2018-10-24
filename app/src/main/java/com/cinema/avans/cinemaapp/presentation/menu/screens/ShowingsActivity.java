package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.logic.additional.SeatSelector;
import com.cinema.avans.cinemaapp.logic.additional.ShowingsComparator;
import com.cinema.avans.cinemaapp.presentation.menu.adapters.ShowingsListAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class ShowingsActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_showings);

        // Get Movie from MovieDetailedActivity
        if (getIntent().getExtras() != null) movie = (Movie) getIntent().getExtras().getSerializable("MOVIE");

        // Get showings
        ArrayList<Showing> showings = movie.getShowings();
        // Sort showings
        Collections.sort(showings, new ShowingsComparator());
        // Check if movie has no showings and alert user
        if (showings.size() == 0) {
            Toast.makeText(getApplicationContext(), "No showings for: " + movie.getTitle(), Toast.LENGTH_LONG).show();
        }

        // Setup adapter
        ListView movieShowingsListView = findViewById(R.id.detailedMovieShowingsListView);
        final ShowingsListAdapter showingsListAdapter = new ShowingsListAdapter(getApplicationContext(), showings);
        movieShowingsListView.setAdapter(showingsListAdapter);
        movieShowingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShowingsActivity.this, SeatSelectorActivity.class);
                intent.putExtra("SEAT_SELECTOR", new SeatSelector(
                        showingsListAdapter.getItem(i),
                        showingsListAdapter.getItem(i).getHallInstance(),
                        1));
                intent.putExtra("USER", Session.user);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

}
