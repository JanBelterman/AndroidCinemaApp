package com.cinema.avans.cinemaapp.logic.user;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.dataAccess.localRepositories.ShowingRepository;
import com.cinema.avans.cinemaapp.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class ShowingsGetter extends AsyncTask<Movie, Void, ArrayList<Showing>> {

    private ShowingRepository showingRepository;
    private ShowingsListener showingsListener;

    public ShowingsGetter(ShowingRepository showingRepository, ShowingsListener showingsListener) {
        this.showingRepository = showingRepository;
        this.showingsListener = showingsListener;

    }

    @Override
    protected ArrayList<Showing> doInBackground(Movie... movies) {

        Movie movie = movies[0];

        return showingRepository.getShowings(movie);

    }

    @Override
    protected void onPostExecute(ArrayList<Showing> showings) {

        showingsListener.showingsFound(showings);

    }

}
