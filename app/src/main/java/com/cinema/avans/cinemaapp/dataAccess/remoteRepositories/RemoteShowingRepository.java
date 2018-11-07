package com.cinema.avans.cinemaapp.dataAccess.remoteRepositories;

import android.app.Activity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.HallInstance;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.logic.callbacks.ShowingsCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 24 October 2018
 */
public class RemoteShowingRepository {

    private ShowingsCallback callback;
    private Activity activity;

    public RemoteShowingRepository(Activity activity, ShowingsCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public void getForMovie(final Movie movie) {
        String url = "https://cinema-app-api.herokuapp.com/api/showings/" + movie.getID();
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Showing> showings = new ArrayList<>();
                    // Get showings
                    JSONArray showingsFromApi = new JSONArray(response);
                    for (int i = 0; i < showingsFromApi.length(); i++) {
                        // Get showing
                        JSONObject showingFromApi = showingsFromApi.getJSONObject(i);
                        Showing showing = new Showing();
                        showing.setID(showingFromApi.getInt("ID"));
                        showing.getHallInstance().setID(showingFromApi.getInt("hallInstanceID"));
                        showing.setMovie(movie);
                        showing.setDate(showingFromApi.getString("date"));
                        showings.add(showing);
                    }
                    callback.showingsFound(showings);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.error("Error getting movies");
            }
        }){
            @Override
            public Map<String, String> getHeaders () {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Session.user.getToken());
                return headers;
            }};
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

}
