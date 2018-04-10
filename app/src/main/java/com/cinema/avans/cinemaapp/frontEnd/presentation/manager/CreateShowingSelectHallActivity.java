package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.logic.manager.HallInstanceFactory;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

// Step 2: select hall
public class CreateShowingSelectHallActivity extends AppCompatActivity {

    private Showing showing;
    private ArrayList<Hall> halls;
    private HallInstanceFactory hallInstanceFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_select_hall);

        setupActivity();

        // Display halls
        final HallAdapter hallAdapter = new HallAdapter(getApplicationContext(), halls);
        ListView hallListView = findViewById(R.id.createShowingSelectHallListView);
        hallListView.setAdapter(hallAdapter);

        // Set click listener
        hallListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Set hall
                showing.setHallInstance(hallInstanceFactory.createHallInstance(hallAdapter.getItem(i)));

                // Start next activity and pass trough the showing with the HallInstance and Movie
                Intent intent = new Intent(CreateShowingSelectHallActivity.this, CreateShowingSelectDateActivity.class);
                intent.putExtra("SHOWING", showing);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

            }
        });

    }

    private void setupActivity() {

        // Get showing and halls
        Intent intent = getIntent();
        showing = (Showing) intent.getSerializableExtra("SHOWING");
        halls = (ArrayList<Hall>) intent.getSerializableExtra("HALLS");

        // Create a hall instance factory
        hallInstanceFactory = new HallInstanceFactory();

    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

}
