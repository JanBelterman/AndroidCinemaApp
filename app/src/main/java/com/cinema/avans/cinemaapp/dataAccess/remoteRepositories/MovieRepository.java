package com.cinema.avans.cinemaapp.dataAccess.remoteRepositories;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.dataAccess.callbacks.MovieGottenCallback;
import com.cinema.avans.cinemaapp.domain.Genre;
import com.cinema.avans.cinemaapp.domain.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 04 July 2018
 */
public class MovieRepository {

    // Manager
    private MovieGottenCallback movieGottenCallback;

    // Activity needed
    private Activity activity;

    // Domain
    private ArrayList<Genre> genres;

    // Constructor
    public MovieRepository(MovieGottenCallback movieGottenCallback, Activity activity) {

        this.movieGottenCallback = movieGottenCallback;
        this.activity = activity;
        this.genres = new ArrayList<>();

    }

    // Called by manager: sends request to api to get movies
    public void getMovies() {

        getGenresWithVolley();

    }

    // Get genres and proceed with getting the movies
    private void getGenresWithVolley() {

        String url = "https://cinema-app-api.herokuapp.com/api/genre";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    // Get genres
                    JSONArray genresFromApi = new JSONArray(response);
                    for (int i = 0; i < genresFromApi.length(); i++) {

                        JSONObject genreFromApi = genresFromApi.getJSONObject(i);
                        Genre genre = new Genre();
                        genre.setID(genreFromApi.getInt("ID"));
                        genre.setTitle(genreFromApi.getString("title"));

                        genres.add(genre);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Get movies
                getMoviesWithVolley();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("UserMoviesFragment", "Error getting genres: " + error);
                movieGottenCallback.showError("Error getting movies");
            }
        }){
            @Override
            // Add headers to the JSON request
            public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MSwiaWF0IjoxNTI4OTgxODYyfQ.geIsA-0DUFZHFLMyb84ZuJbJmU0KuyM3JPvI2Qm4Xug");
                return headers;
            }};

        requestQueue.add(stringRequest);

    }

    // Get movies
    private void getMoviesWithVolley() {

        String url = "https://cinema-app-api.herokuapp.com/api/movie";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    // Get movies
                    JSONArray moviesFromApi = new JSONArray(response);
                    for (int i = 0; i < moviesFromApi.length(); i++) {

                        JSONObject movieFromApi = moviesFromApi.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setID(movieFromApi.getInt("ID"));
                        movie.setTitle(movieFromApi.getString("title"));
                        movie.setDescription(movieFromApi.getString("description"));
                        movie.setRuntime(movieFromApi.getInt("runtime"));
                        movie.setDirector(movieFromApi.getString("director"));
                        movie.setProduction(movieFromApi.getString("production"));
                        movie.setReleaseDate(movieFromApi.getString("releaseDate"));
                        movie.setRating(movieFromApi.getDouble("rating"));
                        movie.setImageUrl(movieFromApi.getString("imageURL"));
                        movie.setGenre(new Genre());
                        int genreID = movieFromApi.getInt("genreID");
                        for (Genre genre : genres) {
                            if (genre.getID() == genreID) {
                                movie.getGenre().setID(genre.getID());
                                movie.getGenre().setTitle(genre.getTitle());
                            }
                        }

                        movieGottenCallback.movieGotten(movie);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("UserMoviesFragment", "Error getting movies: " + error);
                movieGottenCallback.showError("Error getting movies");
            }
        }){
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MSwiaWF0IjoxNTI4OTgxODYyfQ.geIsA-0DUFZHFLMyb84ZuJbJmU0KuyM3JPvI2Qm4Xug");
                return headers;
            }};

        requestQueue.add(stringRequest);

    }

}
