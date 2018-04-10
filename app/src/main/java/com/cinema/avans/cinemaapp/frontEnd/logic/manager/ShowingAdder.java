package com.cinema.avans.cinemaapp.frontEnd.logic.manager;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.ShowingRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class ShowingAdder extends AsyncTask<Showing, Void, Void> {

    private ShowingAddedListener showingAddedListener;
    private ShowingRepository showingRepository;

    public ShowingAdder(ShowingAddedListener showingAddedListener
            ,ShowingRepository showingRepository) {

        this.showingAddedListener = showingAddedListener;
        this.showingRepository = showingRepository;

    }

    @Override
    protected Void doInBackground(Showing... showings) {

        Showing showing = showings[0];

        showingRepository.createShowing(showing);

        return null;

    }

    @Override
    protected void onPostExecute(Void v) {

        showingAddedListener.showingAdded();

    }


}
