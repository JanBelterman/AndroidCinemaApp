package com.cinema.avans.cinemaapp.dataAccess.remoteRepositories;

import android.app.Activity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.logic.callbacks.PaymentCallback;
import com.cinema.avans.cinemaapp.logic.callbacks.TicketCallback;

import org.json.JSONArray;
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
    private TicketCallback ticketCallback;

    public RemoteTicketRepository(Activity activity, PaymentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }
    public RemoteTicketRepository(Activity activity, TicketCallback ticketCallback) {
        this.activity = activity;
        this.ticketCallback = ticketCallback;
    }

    public void addAll(final ArrayList<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            // Request body
            Map<String, String> body = new HashMap<>();
            body.put("showingID", String.valueOf(ticket.getShowing().getID()));
            body.put("seatInstanceID", String.valueOf(ticket.getSeatInstance().getID()));
            // Sending request
            String url = "https://cinema-app-api.herokuapp.com/api/tickets";
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
                            error.printStackTrace();
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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }
    }

    public void getAllForUser() {
        String url = "https://cinema-app-api.herokuapp.com/api/tickets";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request getAllTicketsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Get tickets
                    JSONArray apiTickets = new JSONArray(response);
                    if (apiTickets.length() == 0) ticketCallback.error("User has no tickets");
                    for (int i = 0; i < apiTickets.length(); i++) {
                        // json parse
                        JSONObject apiTicket = apiTickets.getJSONObject(i);
                        Ticket ticket = new Ticket();
                        ticket.setID(apiTicket.getInt("ID"));
                        JSONObject apiShowing = apiTicket.getJSONObject("showing");
                        ticket.getShowing().setID(apiShowing.getInt("ID"));
                        JSONObject apiHallInstance = apiShowing.getJSONObject("hallInstance");
                        ticket.getShowing().getHallInstance().setID(apiHallInstance.getInt("ID"));
                        JSONObject apiHall = apiHallInstance.getJSONObject("hall");
                        ticket.getShowing().getHallInstance().getHall().setID(apiHall.getInt("ID"));
                        ticket.getShowing().getHallInstance().getHall().setHallNr(apiHall.getInt("hallNr"));
                        JSONObject apiMovie = apiShowing.getJSONObject("movie");
                        ticket.getShowing().getMovie().setID(apiMovie.getInt("ID"));
                        ticket.getShowing().getMovie().setTitle(apiMovie.getString("title"));
                        ticket.getShowing().setDate(apiShowing.getString("date"));
                        JSONObject apiSeatInstance = apiTicket.getJSONObject("seatInstance");
                        ticket.getSeatInstance().setID(apiSeatInstance.getInt("ID"));
                        JSONObject apiSeat = apiSeatInstance.getJSONObject("seat");
                        ticket.getSeatInstance().getSeat().setID(apiSeat.getInt("ID"));
                        ticket.getSeatInstance().getSeat().setSeatNr(apiSeat.getInt("seatNr"));
                        JSONObject apiSeatRow = apiSeat.getJSONObject("seatRow");
                        ticket.getSeatInstance().getSeat().getSeatRow().setID(apiSeatRow.getInt("ID"));
                        ticket.getSeatInstance().getSeat().getSeatRow().setRowNr(apiSeatRow.getInt("rowNr"));
                        ticketCallback.ticketFound(ticket);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ticketCallback.error("Error getting tickets");
            }
        }){
            @Override
            public Map<String, String> getHeaders () {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-auth-token", Session.user.getToken());
                return headers;
            }};
        getAllTicketsRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(getAllTicketsRequest);
    }

}
