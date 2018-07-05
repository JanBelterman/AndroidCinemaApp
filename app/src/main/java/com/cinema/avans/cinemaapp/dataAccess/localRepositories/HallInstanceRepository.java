package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.cinema.SeatRowInstance;
import com.cinema.avans.cinemaapp.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.domain.cinema.HallInstance;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallInstanceRepository {

    private DatabaseManager databaseManager;

    public HallInstanceRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    // When a hall instance is added to the database:
    // - The HallInstance itself has to be added
    // - All of the SeatRowInstances have to be added
    // - All of the SeatInstances per SeatRowInstance have to be added
    void createHallInstance(HallInstance hallInstance) {

        // Add HallInstance and add generated id
        hallInstance.setID(databaseManager.createHallInstance(hallInstance));

        // Add all SeatRow instances
        for (SeatRowInstance seatRowInstance : hallInstance.getSeatRowInstances()) {
            new SeatRowInstanceRepository(databaseManager).createSeatRowInstance(seatRowInstance);

        }

    }

    public HallInstance getHallInstance(Showing showing) {

        // Get HallInstance
        HallInstance hallInstance = databaseManager.getHallInstance(showing.getHallInstance().getID());
        // Also get SeatRowInstances
        hallInstance.setSeatRowInstances(new SeatRowInstanceRepository(databaseManager).getSeatRowInstances(hallInstance));

        // Return HallInstance
        return hallInstance;

    }

}
