package com.cinema.avans.cinemaapp.dataAccess.remoteRepositories;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.logic.callbacks.MovieCallback;
import com.cinema.avans.cinemaapp.domain.Genre;
import com.cinema.avans.cinemaapp.domain.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 04 July 2018
 */
public class RemoteMovieRepository {

    // Manager
    private MovieCallback callback;

    // Activity needed
    private Activity activity;

    // Constructor
    public RemoteMovieRepository(MovieCallback callback, Activity activity) {
        this.callback = callback;
        this.activity = activity;
    }

    // Called by manager -> sends request to api to get movies
    public void getWithGenre() {
        String url = "https://cinema-app-api.herokuapp.com/api/movie?include=genre";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Get movies
                    JSONArray moviesFromApi = new JSONArray(response);
                    if (moviesFromApi.length() == 0) callback.errorGotten("No movies");
                    for (int i = 0; i < moviesFromApi.length(); i++) {
                        // Get movie
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
                        movie.setImageUrl(movieFromApi.getString("imageUrl"));
                        movie.setGenre(new Genre(
                                movieFromApi.getInt("genreID"),
                                movieFromApi.getString("genre")
                        ));
                        callback.movieGotten(movie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.errorGotten("Error getting movies");
            }
        }){
            @Override
            public Map<String, String> getHeaders () {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Session.user.getToken());
                return headers;
            }};
        requestQueue.add(stringRequest);
    }

}
