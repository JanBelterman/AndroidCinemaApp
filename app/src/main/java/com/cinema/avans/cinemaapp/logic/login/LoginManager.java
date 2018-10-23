package com.cinema.avans.cinemaapp.logic.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cinema.avans.cinemaapp.domain.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LoginManager {

    private LoginCallback loginCallback;
    private Context context;

    public LoginManager(LoginCallback loginCallback, Context context) {
        this.loginCallback = loginCallback;
        this.context = context;
    }

    public void login(final String username, final String password) {

        // Request body
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        // Sending request
        String url = "https://cinema-app-api.herokuapp.com/api/user/login";
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
                    loginCallback.login(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Get error message
                Log.i("Error:", error.toString());
                String message = "Failed to login";
                switch(error.networkResponse.statusCode) {
                    case 401: message = "Invalid credentials";
                }
                loginCallback.falseLogin(message);
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
