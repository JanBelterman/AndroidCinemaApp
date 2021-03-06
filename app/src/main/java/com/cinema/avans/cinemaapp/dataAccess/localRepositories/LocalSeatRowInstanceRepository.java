package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.HallInstance;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.SeatRowInstance;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class LocalSeatRowInstanceRepository {

    private DatabaseManager databaseManager;

    public LocalSeatRowInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeatRowInstance(SeatRowInstance seatRowInstance) {

        // Add the seat row instance
        seatRowInstance.setID(databaseManager.createSeatRowInstanceAndGetGeneratedId(seatRowInstance));

        // Add the SeatInstances within the SeatRow
        for (SeatInstance seatInstance : seatRowInstance.getSeatInstances()) {
            new LocalSeatInstanceRepository(databaseManager).createSeatInstance(seatInstance);

        }

    }

    // Gets everything except the SeatRow
    ArrayList<SeatRowInstance> getSeatRowInstances(HallInstance hallInstance) {

        // Get SeatRowInstances
        ArrayList<SeatRowInstance> seatRowInstances = databaseManager.getSeatRowInstances(hallInstance);
        // For each of the SeatRowInstances, also get the SeatInstances
        for (SeatRowInstance seatRowInstance : seatRowInstances) {

            seatRowInstance.setSeatInstances(new LocalSeatInstanceRepository(databaseManager).getSeatInstances(seatRowInstance));
            // Also add the HallInstance
            seatRowInstance.setHallInstance(hallInstance);

        }

        // Return SeatRowInstances
        return seatRowInstances;

    }

}
