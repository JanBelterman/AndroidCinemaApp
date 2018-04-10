package com.cinema.avans.cinemaapp.frontEnd.logic.manager;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.HallRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class HallRetriever extends AsyncTask<Void, Void, ArrayList<Hall>> {

    private HallsRetrievedListener hallsRetrievedListener;
    private HallRepository hallRepository;

    public HallRetriever(HallsRetrievedListener hallsRetrievedListener,
                         HallRepository hallRepository) {

        this.hallsRetrievedListener = hallsRetrievedListener;
        this.hallRepository = hallRepository;

    }

    @Override
    protected ArrayList<Hall> doInBackground(Void... voids) {

        return hallRepository.getAllHalls();

    }

    @Override
    protected void onPostExecute(ArrayList<Hall> halls) {

        hallsRetrievedListener.hallsRetrieved(halls);

    }
}
