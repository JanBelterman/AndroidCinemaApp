package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import android.util.Log;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatInstanceRepository {

    private DatabaseManager databaseManager;

    public SeatInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeatInstance(SeatInstance seatInstance) {

        // Log
        Log.i("SeatInstanceRepository", "Creating : " + seatInstance);
        // Create SeatInstance
        databaseManager.createSeatInstance(seatInstance);

    }

    ArrayList<SeatInstance> getSeatInstances(SeatRowInstance seatRowInstance) {

        // Log action
        Log.i("SeatInstanceRepository", "Asking database for all SeatInstances withing " + seatRowInstance);

        // Get all SeatInstances within the SeatRowInstance given as parameter
        ArrayList<SeatInstance> seatInstances = databaseManager.getSeatInstances(seatRowInstance);
        for (SeatInstance seatInstance : seatInstances) {

            databaseManager.getSeatInstances(seatRowInstance);
            // Also add the SeatRowInstance
            seatInstance.setSeatRowInstance(seatRowInstance);

            // Log complete SeatInstance
            Log.i("Database", "Complete SeatInstance: " + seatInstance);

        }

        // Return SeatInstances
        return seatInstances;

    }

    public void updateSeats(ArrayList<SeatInstance> seatInstances) {

        for (SeatInstance seatInstance : seatInstances) {

            // Log
            Log.i("SeatInstanceRepository", "Updating " + seatInstance);

            databaseManager.updateSeatInstance(seatInstance);

        }


    }

}
