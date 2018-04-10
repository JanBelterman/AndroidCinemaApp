package com.cinema.avans.cinemaapp.frontEnd.logic.logIn;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.UserRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 05 April 2018
 */

public class GetUserAsync extends AsyncTask<User, Void, User> {

    private UserGottenListener userGottenListener;
    private UserRepository userRepository;

    public GetUserAsync(UserGottenListener userGottenListener,
                        UserRepository userRepository) {

        this.userGottenListener = userGottenListener;
        this.userRepository = userRepository;

    }

    @Override
    protected User doInBackground(User... users) {

        return userRepository.getUser(users[0].getUsername());

    }

    @Override
    protected void onPostExecute(User user) {

        userGottenListener.userGotten(user);

    }

}
