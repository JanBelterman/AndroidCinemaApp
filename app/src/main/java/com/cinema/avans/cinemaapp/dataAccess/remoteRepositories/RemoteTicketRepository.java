package com.cinema.avans.cinemaapp.dataAccess.remoteRepositories;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.logic.callbacks.PaymentCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 24 October 2018
 */
public class RemoteTicketRepository {

    private Activity activity;
    private PaymentCallback callback;

    public RemoteTicketRepository(Activity activity, PaymentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public void addAll(final ArrayList<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            // Request body
            Map<String, String> body = new HashMap<>();
            body.put("showingID", String.valueOf(ticket.getShowing().getID()));
            body.put("seatInstanceID", String.valueOf(ticket.getSeatInstance().getID()));

            // Sending request
            String url = "https://cinema-app-api.herokuapp.com/api/ticket";
            RequestQueue requestQueue = Volley.newRequestQueue(activity);
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            url,
                            new JSONObject(body),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        callback.success();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Get error message
                            Log.i("Error:", error.toString());
                            callback.error("Failed to purchase");
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("Content-Type", "application/json; charset=utf-8");
                            headers.put("x-auth-token", Session.user.getToken());
                            return headers;
                        }
                    };

            requestQueue.add(jsonObjectRequest);
        }
    }

}
