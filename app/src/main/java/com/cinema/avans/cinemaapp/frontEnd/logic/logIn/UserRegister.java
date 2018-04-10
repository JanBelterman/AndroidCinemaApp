package com.cinema.avans.cinemaapp.frontEnd.logic.logIn;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class UserRegister {

    private RepositoryFactory repositoryFactory;

    public UserRegister(RepositoryFactory repositoryFactory) {

        this.repositoryFactory = repositoryFactory;

    }

    public void createUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        repositoryFactory.getUserRepository().createUser(user);

    }

}
