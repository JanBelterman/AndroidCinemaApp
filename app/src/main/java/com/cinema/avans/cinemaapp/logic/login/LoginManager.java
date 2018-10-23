package com.cinema.avans.cinemaapp.logic.login;

import android.content.Context;

import com.cinema.avans.cinemaapp.dataAccess.callbacks.UserGottenCallback;
import com.cinema.avans.cinemaapp.dataAccess.remoteRepositories.UserRepository;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LoginManager implements UserGottenCallback {

    // Callback to presentation
    private LoginCallback loginCallback;

    // Data access
    private UserRepository userRepository;

    public LoginManager(LoginCallback loginCallback, Context context) {

        this.loginCallback = loginCallback;
        this.userRepository = new UserRepository(this, context);

    }

    public void login(String username, String password) {

        userRepository.login(username, password);

    }

    @Override
    public void userGotten(User user) {

        loginCallback.login(user);

    }

    @Override
    public void noUserGotten(String message) {

        loginCallback.falseLogin(message);

    }

}
