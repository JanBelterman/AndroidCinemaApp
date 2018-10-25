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
import com.cinema.avans.cinemaapp.domain.Hall;
import com.cinema.avans.cinemaapp.domain.HallInstance;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.SeatRowInstance;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.logic.callbacks.HallInstanceCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 24 October 2018
 */
public class RemoteHallInstanceRepository {

    private HallInstanceCallback callback;
    private Activity activity;

    public RemoteHallInstanceRepository(Activity activity, HallInstanceCallback callback) {
        this.callback = callback;
        this.activity = activity;
    }

    public void getForShowing(Showing showing) {
        String url = "https://cinema-app-api.herokuapp.com/api/hallInstance/" + showing.getHallInstance().getID();
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        Request stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Get hall instance
                    JSONObject hallInstanceFromApi = new JSONObject(response);
                    HallInstance hallInstance = new HallInstance();
                    hallInstance.setID(hallInstanceFromApi.getInt("ID"));
                    hallInstance.setHall(new Hall());
                    hallInstance.getHall().setID(hallInstanceFromApi.getInt("hallID"));
                    // Get seat row instances
                    JSONArray seatRowInstancesFromApi = hallInstanceFromApi.getJSONArray("seatRowInstances");
                    for (int i = 0; i < seatRowInstancesFromApi.length(); i++) {
                        JSONObject seatRowInstanceFromApi = seatRowInstancesFromApi.getJSONObject(i);
                        SeatRowInstance seatRowInstance = new SeatRowInstance();
                        seatRowInstance.setID(seatRowInstanceFromApi.getInt("ID"));
                        seatRowInstance.setHallInstance(hallInstance);
                        seatRowInstance.getSeatRow().setID(seatRowInstanceFromApi.getInt("seatRowID"));
                        // Get seats
                        JSONArray seatInstancesFromApi = seatRowInstanceFromApi.getJSONArray("seatInstances");
                        for (int j = 0; j < seatInstancesFromApi.length(); j++) {
                            JSONObject seatInstanceFromApi = seatInstancesFromApi.getJSONObject(j);
                            SeatInstance seatInstance = new SeatInstance();
                            seatInstance.setID(seatInstanceFromApi.getInt("ID"));
                            seatInstance.setSeatRowInstance(seatRowInstance);
                            seatInstance.getSeat().setID(seatInstanceFromApi.getInt("seatID"));
                            seatInstance.setStatus(seatInstanceFromApi.getInt("status"));
                            seatRowInstance.getSeatInstances().add(seatInstance);
                        }
                        hallInstance.getSeatRowInstances().add(seatRowInstance);
                    }
                    callback.hallInstanceFound(hallInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.error("Something went wrong");
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
