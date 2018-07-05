package com.cinema.avans.cinemaapp.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.NewMovieListener;
import com.cinema.avans.cinemaapp.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.logic.manager.MovieFactory;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateMovieActivity extends AppCompatActivity implements NewMovieListener {

    private MovieFactory movieFactory;

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

                    // Send post request to api to add movie
                    // Create JSON body
                    JSONObject jsonBody = new JSONObject();
                    try {

                        // Put attributes
                        jsonBody.put("title", movie.getTitle());
                        jsonBody.put("description", movie.getDescription());
                        jsonBody.put("runtime", 122);
                        jsonBody.put("genreID", 2.0); // Genre object inside of movie
                        jsonBody.put("director", movie.getDirector());
                        jsonBody.put("production", movie.getProduction());
                        jsonBody.put("releaseDate", movie.getReleaseDate());
                        jsonBody.put("rating", movie.getRating());
                        jsonBody.put("imageURL", movie.getImageUrl());
                        // Log body
                        Log.i("CreateMovie", "JSON body: " + jsonBody);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Create request queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    // Create url
                    String url = "https://cinema-app-api.herokuapp.com/api/movie";

                    // Create request
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                            new Response.Listener<JSONObject>() {
                                // On successful response
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.i("LOG_RESPONSE", String.valueOf(response));
                                    // Show message and finish activity
                                    Toast.makeText(getApplicationContext(), R.string.movieAdded, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }, new Response.ErrorListener() {
                        // When an error occurs
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("CreateMovieActivity", error.toString());
                        }
                    }){
                        // Add headers to the JSON request
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("x-auth-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MSwiaWF0IjoxNTI4OTgxODYyfQ.geIsA-0DUFZHFLMyb84ZuJbJmU0KuyM3JPvI2Qm4Xug");
                            return headers;
                        }
                    };

                    // Add the request to the request queue
                    requestQueue.add(jsonObjectRequest);

                }

            }
        });

    }

    // Listener for when async task found a new movie
    @Override
    public void newApiMovie(Movie movie) {

        // Set current movie
        this.movie = movie;
        // Display movie
        displayMovie();

    }

    // Displays movie information
    private void displayMovie() {

        // Set image
        ImageView movieImage = findViewById(R.id.createMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);
        // Set title
        TextView movieTitle = findViewById(R.id.createMovieTitleText);
        movieTitle.setText(movie.getTitle());

    }

}
