package com.cinema.avans.cinemaapp.logic.register;

import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class RegisterManager {

    private RepositoryFactory repositoryFactory;

    public RegisterManager(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        repositoryFactory.getUserRepository().createUser(user);
    }

}
