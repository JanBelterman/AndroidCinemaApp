package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;
import com.cinema.avans.cinemaapp.frontEnd.logic.user.SeatSelector;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class MovieDetailedShowingsActivity extends AppCompatActivity {

    private Movie movie;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie_showings);

        // Get Movie from MovieDetailedActivity
        movie = (Movie) getIntent().getExtras().getSerializable("MOVIE");
        user = (User) getIntent().getSerializableExtra("USER");

        Log.i("MovieDetailedShowAct", "User gotten: " + user);

        if (movie.getShowings().size() == 0) {
            Toast.makeText(getApplicationContext(), "No showings for: " + movie.getTitle(), Toast.LENGTH_LONG).show();

        }

        // fix Sorter for time
        ListView movieShowingsListView = findViewById(R.id.detailedMovieShowingsListView);
        final ShowingsAdapter showingsAdapter = new ShowingsAdapter(getApplicationContext(), movie.getShowings());
        movieShowingsListView.setAdapter(showingsAdapter);

        movieShowingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MovieDetailedShowingsActivity.this, SeatSelectorActivity.class);
                intent.putExtra("SEAT_SELECTOR", new SeatSelector(
                        showingsAdapter.getItem(i),
                        showingsAdapter.getItem(i).getHallInstance(),
                        1));
                intent.putExtra("USER", user);
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
