package com.cinema.avans.cinemaapp.logic.register;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.User;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class RegisterManager {

    private Context context;
    // Callback to presentation
    private RegisterCallback registerCallback;

    public RegisterManager(Context context, RegisterCallback registerCallback) {
        this.context = context;
        this.registerCallback = registerCallback;
    }

    public void createUser(String username, String password) {

        // Request body
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        // Sending request
        String url = "https://cinema-app-api.herokuapp.com/api/user/register";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        new JSONObject(body),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Get user
                                    User user = new User();
                                    user.setUsername(response.getString("username"));
                                    user.setPassword(response.getString("password"));
                                    user.setToken(response.getString("token"));
                                    registerCallback.login(user);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Get error message
                        Log.i("Error:", error.toString());
                        String message = "Failed to register";
                        switch(error.networkResponse.statusCode) {
                            case 405:
                                try {
                                    message = new String(error.networkResponse.data, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    message = "User already exists";
                                }
                        }
                        registerCallback.falseRegister(message);
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };

        requestQueue.add(jsonObjectRequest);

    }

}
