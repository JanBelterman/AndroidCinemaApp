package com.cinema.avans.cinemaapp.dataAccess.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cinema.avans.cinemaapp.domain.Genre;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.domain.Ticket;
import com.cinema.avans.cinemaapp.domain.Hall;
import com.cinema.avans.cinemaapp.domain.HallInstance;
import com.cinema.avans.cinemaapp.domain.Seat;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.SeatRow;
import com.cinema.avans.cinemaapp.domain.SeatRowInstance;
import com.cinema.avans.cinemaapp.domain.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class DatabaseManager extends SQLiteOpenHelper {

    //////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    //////////////////////////////////////////////////////////////////////////////////////////////

    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Creating tables
        sqLiteDatabase.execSQL(CREATE_TABLE_HALL); // Create
        Log.i("Database", CREATE_TABLE_HALL); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_ROW); // Create
        Log.i("Database", CREATE_TABLE_SEAT_ROW); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT); // Create
        Log.i("Database", CREATE_TABLE_SEAT); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_HALL_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_HALL_INSTANCE); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_ROW_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_SEAT_ROW_INSTANCE); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_SEAT_INSTANCE); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE); // Create
        Log.i("Database", CREATE_TABLE_MOVIE); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_SHOWING); // Create
        Log.i("Database", CREATE_TABLE_SHOWING); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_TICKET); // Create
        Log.i("Database", CREATE_TABLE_TICKET); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_LOG_IN); // Create
        Log.i("Database", CREATE_TABLE_LOG_IN); // Print

        sqLiteDatabase.execSQL(CREATE_TABLE_USER); // Create
        Log.i("Database", CREATE_TABLE_USER); // Print

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_ROW);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_ROW_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG_IN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWING);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET);

        // create new tables
        onCreate(sqLiteDatabase);

    }

    // HALL
    public void createHall(Hall hall) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();

        // Create Hall
        ContentValues hallValues = new ContentValues();
        hallValues.put(HALL_COLUMN_HALL_NR, hall.getHallNr());
        // Insert Hall
        database.insert(TABLE_HALL, null, hallValues);

    }
    public Hall getHall(int hallNr) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_HALL + "\n"
                        + "WHERE " + HALL_COLUMN_HALL_NR + " = " + hallNr;
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Create Hall
        Hall hall = new Hall();
        if (cursor.moveToFirst()) {

            // Set values
            hall.setHallNr(cursor.getInt(cursor.getColumnIndex(HALL_COLUMN_HALL_NR)));

        }

        // Close cursor and return Hall
        cursor.close();
        return hall;

    }
    public ArrayList<Hall> getAllHalls() {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_HALL;
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get Halls
        ArrayList<Hall> halls = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Create Hall
                Hall hall = new Hall();
                hall.setHallNr(cursor.getInt(cursor.getColumnIndex(HALL_COLUMN_HALL_NR)));

                // Add and log Hall
                halls.add(hall);

                // Next
                cursor.moveToNext();

            }

        }

        // Close cursor and return Halls
        cursor.close();
        return halls;

    }

    // SEAT_ROW
    public SeatRow createSeatRow(SeatRow seatRow) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();

        // Create SetRow
        ContentValues values = new ContentValues();
        values.put(SEAT_ROW_COLUMN_HALL_NR, seatRow.getHall().getHallNr());
        values.put(SEAT_ROW_COLUMN_ROW_NR, seatRow.getRowNr());
        // Insert SeatRow
        int genId = (int) database.insert(TABLE_SEAT_ROW, null, values);

        // SetSeatRow its generated Id
        seatRow.setID(genId);
        // Return SeatRow
        return seatRow;

    }
    public ArrayList<SeatRow> getSeatRows(int hallNr) {

        ArrayList<SeatRow> seatRows = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW + "\n"
                        + "WHERE " + SEAT_ROW_COLUMN_HALL_NR + " = " + hallNr;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {

                SeatRow seatRow = new SeatRow();
                seatRow.setHall(getHall(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_HALL_NR))));
                seatRow.setID(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_ID)));
                seatRow.setRowNr(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_NR)));
                seatRow.setSeats(getSeats(seatRow.getID()));

                seatRows.add(seatRow);

                cursor.moveToNext();

            }

        }

        cursor.close();

        return seatRows;

    }
    private SeatRow getSeatRow(int rowId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW + "\n"
                        + "WHERE " + SEAT_ROW_COLUMN_ROW_ID + " = " + rowId;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            SeatRow seatRow = new SeatRow();
            seatRow.setHall(getHall(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_HALL_NR))));
            seatRow.setID(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_ID)));
            seatRow.setRowNr(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_NR)));

            cursor.close();

            return seatRow;

        }

        cursor.close();

        return null;

    }

    // SEAT
    public void createSeat(Seat seat) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();
        // Create Seat
        ContentValues values = new ContentValues();
        values.put(SEAT_COLUMN_ROW_ID, seat.getSeatRow().getID());
        values.put(SEAT_COLUMN_SEAT_NR, seat.getSeatNr());
        values.put(SEAT_COLUMN_SEAT_VALUE, seat.getSeatValueInt());
        // Insert Seat
        database.insert(TABLE_SEAT, null, values);

    }
    public ArrayList<Seat> getSeats(int rowId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT + "\n"
                        + "WHERE " + SEAT_COLUMN_ROW_ID + " = " + rowId;

        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Seat> seats = new ArrayList<>();

        if (cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {

                Seat seat = new Seat();
                seat.setID(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_ID)));
                seat.setSeatNr(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_NR)));
                seat.setValue(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_VALUE)));
                seat.setSeatRow(getSeatRow(rowId));

                seats.add(seat);

                cursor.moveToNext();

            }

        }

        cursor.close();

        return seats;

    }
    private Seat getSeat(int seatId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_SEAT + "\n"
                + "WHERE " + SEAT_COLUMN_SEAT_ID + " = " + seatId;

        Cursor cursor = database.rawQuery(query, null);

        Seat seat = new Seat();

        if (cursor.moveToFirst()) {

            seat.setSeatNr(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_NR)));
            seat.setValue(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_VALUE)));
            seat.setSeatRow(getSeatRow(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_ROW_ID))));
            seat.setID(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_ID)));


        }

        cursor.close();

        return seat;

    }

    // HALL_INSTANCE
    public int createHallInstance(HallInstance hallInstance) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();

        // Create HallInstance
        ContentValues values = new ContentValues();
        values.put(HALL_INSTANCE_COLUMN_HALL_NR, hallInstance.getHall().getHallNr());
        // Insert HallInstance
        int genId = (int) database.insert(TABLE_HALL_INSTANCE, null, values);

        // Return generated id
        return genId;

    }
    public HallInstance getHallInstance(int hallInstanceId) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_HALL_INSTANCE + "\n"
                + "WHERE " + HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID + " = " + hallInstanceId;

        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Create HallInstance
        HallInstance hallInstance = new HallInstance();
        if (cursor.moveToFirst()) {

            // Get values
            hallInstance.setID(cursor.getInt(cursor.getColumnIndex(HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID)));
            // Also set the Hall of which this HallInstance is an instance of its hallNr
            Hall hall = new Hall();
            hall.setHallNr(cursor.getInt(cursor.getColumnIndex(HALL_COLUMN_HALL_NR)));
            hallInstance.setHall(hall);

        }

        // Close cursor and return HallInstance
        cursor.close();
        return hallInstance;

    }

    // SEAT_ROW_INSTANCE
    public int createSeatRowInstanceAndGetGeneratedId(SeatRowInstance seatRowInstance) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();
        // Create SeatRowInstance
        ContentValues values = new ContentValues();
        values.put(SEAT_ROW_INSTANCE_COLUMN_ROW_ID, seatRowInstance.getSeatRow().getID());
        values.put(SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID, seatRowInstance.getHallInstance().getID());
        // Insert SeatRowInstance
        int genId = (int) database.insert(TABLE_SEAT_ROW_INSTANCE, null, values);

        // Return generated id
        return genId;

    }
    public ArrayList<SeatRowInstance> getSeatRowInstances(HallInstance hallInstance) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW_INSTANCE + "\n"
                        + "WHERE " + SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID + " = " + hallInstance.getID();
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get SeatRowInstances for database
        ArrayList<SeatRowInstance> seatRowInstances = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Create SeatRow
                SeatRowInstance seatRowInstance = new SeatRowInstance();
                seatRowInstance.setID(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID)));
                seatRowInstances.add(seatRowInstance);

                // Next
                cursor.moveToNext();

            }

        }

        // Alert
        if (seatRowInstances.size() == 0) {
            Log.i("Database", "No SeatRowInstances found for " + hallInstance);

        }

        // Close cursor and return SeatRowInstances
        cursor.close();
        return seatRowInstances;

    }

    // SEAT_INSTANCE
    public void createSeatInstance(SeatInstance seatInstance) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();
        // Create SeatInstance
        ContentValues values = new ContentValues();
        values.put(SEAT_INSTANCE_COLUMN_SEAT_ID, seatInstance.getSeat().getID());
        values.put(SEAT_INSTANCE_COLUMN_STATUS, seatInstance.getStatusInt()); // 1 = Available, 2 = Reserved, 3 = Gap
        values.put(SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID, seatInstance.getSeatRowInstance().getID());
        // Insert Seat
        database.insert(TABLE_SEAT_INSTANCE, null, values);

    }
    public void updateSeatInstance(SeatInstance seatInstance) {

        // Get database
        SQLiteDatabase database = getWritableDatabase();

        ContentValues updatedValues = new ContentValues();
        updatedValues.put(SEAT_INSTANCE_COLUMN_STATUS, seatInstance.getStatusInt());

        database.update(TABLE_SEAT_INSTANCE, updatedValues,
                SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID + "=" + seatInstance.getID(),
                null);

    }
    public ArrayList<SeatInstance> getSeatInstances(SeatRowInstance seatRowInstance) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();
        // Make query
        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_INSTANCE + "\n"
                        + "WHERE " + SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID + " = " + seatRowInstance.getID();
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get SeatInstances
        ArrayList<SeatInstance> seatInstances = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Create SeatInstance
                SeatInstance seatInstance = new SeatInstance();
                seatInstance.setID(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID)));
                seatInstance.setStatus(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_STATUS)));
                seatInstances.add(seatInstance);

                // Next
                cursor.moveToNext();

            }

        }

        // Alert
        if (seatInstances.size() == 0) {
            Log.i("Database", "No SeatInstances found for " + seatRowInstance);

        }

        // Close cursor and return SeatInstances
        cursor.close();
        return seatInstances;

    }
    private SeatInstance getSeatInstance(int seatInstanceId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_INSTANCE + "\n"
                        + "WHERE " + SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID + " = " + seatInstanceId;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            SeatInstance seatInstance = new SeatInstance();
            seatInstance.setStatus(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_STATUS)));
            seatInstance.setSeat(getSeat(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_ID))));
            seatInstance.setID(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID)));

            cursor.close();

            return seatInstance;

        }

        cursor.close();

        return null;

    }

    // MOVIE
    public void createMovie(Movie movie) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MOVIE_COLUMN_TITLE, movie.getTitle());
        values.put(MOVIE_COLUMN_DESCRIPTION, movie.getDescription());
        values.put(MOVIE_COLUMN_RUNTIME, movie.getRuntime());
        values.put(MOVIE_COLUMN_GENRE, movie.getRuntime());
        values.put(MOVIE_COLUMN_DIRECTOR, movie.getDirector());
        values.put(MOVIE_COLUMN_ACTORS, movie.getActors());
        values.put(MOVIE_COLUMN_PRODUCTION, movie.getProduction());
        values.put(MOVIE_COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(MOVIE_COLUMN_RATING, movie.getRating());
        values.put(MOVIE_COLUMN_IMAGE_URL, movie.getImageUrl());

        database.insert(TABLE_MOVIE, null, values);

    }
    public ArrayList<Movie> getAllMovies() {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_MOVIE;
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get movies
        ArrayList<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Get and add movie
                Movie movie = getMovie(cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
                movies.add(movie);

                // Next
                cursor.moveToNext();

            }

        }

        // Alert if no movies have been found
        if (movies.size() == 0) {
            Log.i("Database", "No movies found");
        }

        // Close cursor and return movies
        cursor.close();
        return movies;

    }
    public Movie getMovie(int movieId) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_MOVIE + "\n"
                + "WHERE " + MOVIE_COLUMN_MOVIE_ID + " = " + movieId;
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get movie
        Movie movie = new Movie();
        if (cursor.moveToFirst()) {

            // Get movie values
            movie.setID(cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DESCRIPTION)));
            movie.setRuntime(cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_RUNTIME)));
            movie.setGenre(new Genre());
            movie.getGenre().setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_GENRE)));
            movie.setDirector(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DIRECTOR)));
            movie.setActors(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_ACTORS)));
            movie.setProduction(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_PRODUCTION)));
            movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_RELEASE_DATE)));
            movie.setRating(cursor.getDouble(cursor.getColumnIndex(MOVIE_COLUMN_RATING)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_IMAGE_URL)));

        }

        // Close cursor and return movie
        cursor.close();
        return movie;

    }

    // SHOWING
    public void createShowing(Showing showing) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SHOWING_COLUMN_MOVIE_ID, showing.getMovie().getID());
        values.put(SHOWING_COLUMN_DATE, showing.getDate().toString());
        values.put(SHOWING_COLUMN_HALL_INSTANCE_ID, showing.getHallInstance().getID());

        int genId = (int) database.insert(TABLE_SHOWING, null, values);
        showing.setID(genId);

    }
    public ArrayList<Showing> getShowings(Movie movie) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SHOWING + "\n"
                        + "WHERE " + SHOWING_COLUMN_MOVIE_ID + " = " + movie.getID();
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get Showings
        ArrayList<Showing> showings = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Create and add Showing
                Showing showing = new Showing();
                showing.setID(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_SHOWING_ID)));
                showing.setDate(cursor.getString(cursor.getColumnIndex(SHOWING_COLUMN_DATE)));
                // Set hallInstance its id (so the LocalShowingRepository can get the complete HallInstance)
                HallInstance hallInstance = new HallInstance();
                hallInstance.setID(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_HALL_INSTANCE_ID)));
                showing.setHallInstance(hallInstance);
                showings.add(showing);

                // To next Showing
                cursor.moveToNext();

            }

        }

        // Close cursor and return showings
        cursor.close();
        return showings;

    }
    public Showing getShowing(int showingId) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SHOWING + "\n"
                        + "WHERE " + SHOWING_COLUMN_SHOWING_ID + " = " + showingId;
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get Showing
        Showing showing = new Showing();
        if (cursor.moveToFirst()) {

            // Get values
            showing.setID(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_SHOWING_ID)));
            showing.setDate(cursor.getString(cursor.getColumnIndex(SHOWING_COLUMN_DATE)));
            // Set hallInstance its id (so the LocalShowingRepository can get the complete HallInstance)
            HallInstance hallInstance = new HallInstance();
            hallInstance.setID(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_HALL_INSTANCE_ID)));
            showing.setHallInstance(hallInstance);
            // Set Movie its id (so the LocalShowingRepository can get the complete Movie)
            Movie movieToAdd = new Movie();
            movieToAdd.setID(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_MOVIE_ID)));
            showing.setMovie(movieToAdd);

        }

        // Close cursor and return Showing
        cursor.close();
        return showing;

    }

    // TICKET
    public void createTicket(Ticket ticket) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TICKET_COLUMN_SHOWING_ID, ticket.getShowing().getID());
        values.put(TICKET_COLUMN_SEAT_INSTANCE_ID, ticket.getSeatInstance().getID());
        values.put(TICKET_COLUMN_USER_ID, ticket.getUser().getUsername());

        database.insert(TABLE_TICKET, null, values);

    }
    public Ticket getTicket(int ticketId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_TICKET + "\n"
                        + "WHERE " + TICKET_COLUMN_TICKET_ID + " = " + ticketId;

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            Ticket ticket = new Ticket();
            ticket.setID(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_TICKET_ID)));
            ticket.setShowing(getShowing(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SHOWING_ID))));
            ticket.setSeatInstance(getSeatInstance(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SEAT_INSTANCE_ID))));

            return ticket;

        }

        cursor.close();

        return null;

    }
    public ArrayList<Ticket> getTickets(String userId) {

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Make query
        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_TICKET + "\n"
                + "WHERE " + TICKET_COLUMN_USER_ID + " = '" + userId + "'";
        // Execute query
        Cursor cursor = database.rawQuery(query, null);

        // Get tickets
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                // Get ticket and add
                Ticket ticket = new Ticket();
                ticket.setID(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_TICKET_ID)));
                ticket.setSeatInstance(getSeatInstance(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SEAT_INSTANCE_ID))));
                // Also add a showing with the showingId for this ticket (so the LocalShowingRepository can find the complete Showing)
                Showing showing = new Showing();
                showing.setID(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SHOWING_ID)));
                ticket.setShowing(showing);
                tickets.add(ticket);

                // Next
                cursor.moveToNext();

            }

        }

        // Alert
        if (tickets.size() == 0) {
            Log.i("Database", "No tickets have been found");

        }

        // Close cursor and return tickets
        cursor.close();
        return tickets;

    }

    // USER
    public void createUser(User user) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues logInValues = new ContentValues();
        logInValues.put(LOG_IN_COLUMN_USERNAME, user.getUsername());
        logInValues.put(LOG_IN_COLUMN_PASSWORD, user.getPassword());

        database.insert(TABLE_LOG_IN, null, logInValues);

        ContentValues userValues = new ContentValues();
        userValues.put(USER_COLUMN_USERNAME, user.getUsername());

        database.insert(TABLE_USER, null, userValues);

    }
    public User getUser(String username) {

        // Create user
        User user = new User();

        // Get database
        SQLiteDatabase database = getReadableDatabase();

        // Look for user
        String userQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_USER + "\n"
                        + "WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";
        // Execute query and get result
        Cursor userCursor = database.rawQuery(userQuery, null);
        // Get values from user table
        if (userCursor.moveToFirst()) {

            user.setUsername(userCursor.getString(userCursor.getColumnIndex(USER_COLUMN_USERNAME)));

        }

        if (user.getUsername().isEmpty() || user.getUsername().length() == 0) {
            return null;

        }

        userCursor.close();

        // Look up the rest of the user values from logIn table
        String logInQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_LOG_IN + "\n"
                        + "WHERE " + LOG_IN_COLUMN_USERNAME + " = '" + username + "'";
        // Execute query and get result
        Cursor logInCursor = database.rawQuery(logInQuery, null);
        // Get values from logIn table
        if (logInCursor.moveToFirst()) {

            logInCursor.moveToFirst();

            user.setUsername(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_USERNAME)));
            user.setPassword(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_PASSWORD)));

        }

        logInCursor.close();

        return user;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // DATABASE NAMES
    //////////////////////////////////////////////////////////////////////////////////////////////

    // Database name
    private static final String DATABASE_NAME = "Cinema";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Hall Table
    private static final String TABLE_HALL = "Hall";
    // -------------------------------------------------------------------------------------------
    private static final String HALL_COLUMN_HALL_NR = "HallNr"; // PK Hall number in cinema

    // SeatRowInstance table
    private static final String TABLE_SEAT_ROW = "SeatRow";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_ROW_COLUMN_ROW_ID = "RowId"; //PK
    private static final String SEAT_ROW_COLUMN_HALL_NR = "HallNr"; // Hall this seat row is part off
    private static final String SEAT_ROW_COLUMN_ROW_NR = "RowNr"; // Row number in hall

    // SeatInstance table
    private static final String TABLE_SEAT = "Seat";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_COLUMN_SEAT_ID = "SeatId"; //PK
    private static final String SEAT_COLUMN_ROW_ID = "RowId"; // Row this seat is part of (also stores which hall!)
    private static final String SEAT_COLUMN_SEAT_NR = "SeatNr"; // SeatInstance number in row
    private static final String SEAT_COLUMN_SEAT_VALUE = "SeatValue"; // Value BAD, MODERATE, OK, GOOD, PERFECT

    // HallInstance table
    private static final String TABLE_HALL_INSTANCE = "HallInstance";
    // -------------------------------------------------------------------------------------------
    private static final String HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID = "HallInstanceId"; //PK
    private static final String HALL_INSTANCE_COLUMN_HALL_NR = "HallNr"; // Hall this instance originated from

    // SeatRowInstance instance table
    private static final String TABLE_SEAT_ROW_INSTANCE = "SeatRowInstance";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID = "RowInstanceId"; //PK
    private static final String SEAT_ROW_INSTANCE_COLUMN_ROW_ID = "RowId"; // Row this instance originated from
    private static final String SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID = "HallInstanceId"; // HallInstance this row instance is part off

    // SeatInstanceTable
    private static final String TABLE_SEAT_INSTANCE = "SeatInstance";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID = "SeatInstanceId"; //PK
    private static final String SEAT_INSTANCE_COLUMN_SEAT_ID = "SeatId"; // SeatInstance this instance originated from
    private static final String SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID = "SeatRowInstanceId";
    private static final String SEAT_INSTANCE_COLUMN_STATUS = "Status"; // Status, RESERVED, AVAILABLE, GAP(no seat, just room)

    // Movie table
    private static final String TABLE_MOVIE = "Movie";
    // -------------------------------------------------------------------------------------------
    private static final String MOVIE_COLUMN_MOVIE_ID = "MovieId";
    private static final String MOVIE_COLUMN_TITLE = "Title";
    private static final String MOVIE_COLUMN_DESCRIPTION = "Description";
    private static final String MOVIE_COLUMN_RUNTIME = "Runtime";
    private static final String MOVIE_COLUMN_GENRE = "Genre";
    private static final String MOVIE_COLUMN_DIRECTOR = "Director";
    private static final String MOVIE_COLUMN_ACTORS = "Actors";
    private static final String MOVIE_COLUMN_PRODUCTION = "Production";
    private static final String MOVIE_COLUMN_RELEASE_DATE = "ReleaseDate";
    private static final String MOVIE_COLUMN_RATING = "Rating";
    private static final String MOVIE_COLUMN_IMAGE_URL = "ImageUr";

    // Showing table
    private static final String TABLE_SHOWING = "Showing";
    // -------------------------------------------------------------------------------------------
    private static final String SHOWING_COLUMN_SHOWING_ID = "ShowingId"; // PK
    private static final String SHOWING_COLUMN_HALL_INSTANCE_ID = "HallInstanceId"; // HallNr this showing is in
    private static final String SHOWING_COLUMN_MOVIE_ID = "MovieId"; // Movie this showing shows
    private static final String SHOWING_COLUMN_DATE = "Date"; // Date of the showing

    // Ticket table
    private static final String TABLE_TICKET = "Ticket";
    // -------------------------------------------------------------------------------------------
    private static final String TICKET_COLUMN_TICKET_ID = "TicketId";
    private static final String TICKET_COLUMN_SHOWING_ID = "ShowingId";
    private static final String TICKET_COLUMN_USER_ID = "UserId";
    private static final String TICKET_COLUMN_SEAT_INSTANCE_ID = "SeatInstance";

    // LogIn table
    private static final String TABLE_LOG_IN = "LogIn";
    // -------------------------------------------------------------------------------------------
    private static final String LOG_IN_COLUMN_USERNAME = "Username";
    private static final String LOG_IN_COLUMN_PASSWORD = "Password";

    // User table
    private static final String TABLE_USER = "User";
    // -------------------------------------------------------------------------------------------
    private static final String USER_COLUMN_USERNAME = "Username";

    // Manager table
    private static final String TABLE_MANAGER = "Manager";
    // -------------------------------------------------------------------------------------------
    private static final String MANAGER_COLUMN_USERNAME = "Username";

    //////////////////////////////////////////////////////////////////////////////////////////////
    // CREATE QUERIES
    //////////////////////////////////////////////////////////////////////////////////////////////

    private static final String CREATE_TABLE_HALL =
            "CREATE TABLE " + TABLE_HALL + " (" + "\n"
                    + HALL_COLUMN_HALL_NR + " INTEGER PRIMARY KEY" + ");";

    private static final String CREATE_TABLE_SEAT_ROW =
            "CREATE TABLE " + TABLE_SEAT_ROW + " (" + "\n"
                    + SEAT_ROW_COLUMN_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_ROW_COLUMN_HALL_NR + " INTEGER," + "\n"
                    + SEAT_ROW_COLUMN_ROW_NR + " INTEGER" + ");";

    private static final String CREATE_TABLE_SEAT =
            "CREATE TABLE " + TABLE_SEAT + " (" + "\n"
                    + SEAT_COLUMN_SEAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_COLUMN_ROW_ID + " INTEGER," + "\n"
                    + SEAT_COLUMN_SEAT_NR + " INTEGER," + "\n"
                    + SEAT_COLUMN_SEAT_VALUE + " INTEGER" + ");";

    private static final String CREATE_TABLE_HALL_INSTANCE =
            "CREATE TABLE " + TABLE_HALL_INSTANCE + " (" + "\n"
                    + HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + HALL_INSTANCE_COLUMN_HALL_NR + " INTEGER" + ");";

    private static final String CREATE_TABLE_SEAT_ROW_INSTANCE =
            "CREATE TABLE " + TABLE_SEAT_ROW_INSTANCE + " (" + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_ROW_ID + " INTEGER," + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID + " INTEGER" + ");";

    private static final String CREATE_TABLE_SEAT_INSTANCE =
            "CREATE TABLE " + TABLE_SEAT_INSTANCE + " (" + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_ID + " INTEGER," + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID + " INTEGER," + "\n"
                    + SEAT_INSTANCE_COLUMN_STATUS + " INTEGER" + ");";

    private static final String CREATE_TABLE_MOVIE =
            "CREATE TABLE " + TABLE_MOVIE + " (" + "\n"
                    + MOVIE_COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + MOVIE_COLUMN_TITLE + " TEXT," + "\n"
                    + MOVIE_COLUMN_DESCRIPTION + " TEXT," + "\n"
                    + MOVIE_COLUMN_RUNTIME + " TEXT," + "\n"
                    + MOVIE_COLUMN_GENRE + " TEXT," + "\n"
                    + MOVIE_COLUMN_DIRECTOR + " TEXT," + "\n"
                    + MOVIE_COLUMN_ACTORS + " TEXT," + "\n"
                    + MOVIE_COLUMN_PRODUCTION + " TEXT," + "\n"
                    + MOVIE_COLUMN_RELEASE_DATE + " TEXT," + "\n"
                    + MOVIE_COLUMN_RATING + " TEXT," + "\n"
                    + MOVIE_COLUMN_IMAGE_URL + " TEXT" + ");";

    private static final String CREATE_TABLE_SHOWING =
            "CREATE TABLE " + TABLE_SHOWING + " (" + "\n"
                    + SHOWING_COLUMN_SHOWING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SHOWING_COLUMN_HALL_INSTANCE_ID + " INTEGER," + "\n"
                    + SHOWING_COLUMN_MOVIE_ID + " INTEGER," + "\n"
                    + SHOWING_COLUMN_DATE + " TEXT" + ");"; // YYYY-MM-DD-HH-MM

    private static final String CREATE_TABLE_TICKET =
            "CREATE TABLE " + TABLE_TICKET + " (" + "\n"
                    + TICKET_COLUMN_TICKET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + TICKET_COLUMN_SHOWING_ID + " INTEGER," + "\n"
                    + TICKET_COLUMN_SEAT_INSTANCE_ID + " INTEGER," + "\n"
                    + TICKET_COLUMN_USER_ID + " TEXT" + ");";

    private static final String CREATE_TABLE_LOG_IN =
            "CREATE TABLE " + TABLE_LOG_IN + " (" + "\n"
                    + LOG_IN_COLUMN_USERNAME + " TEXT PRIMARY KEY," + "\n"
                    + LOG_IN_COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_USER + " (" + "\n"
                    + USER_COLUMN_USERNAME + " TEXT PRIMARY KEY" + ")";

    private static final String CREATE_TABLE_MANAGER =
            "CREATE TABLE " + TABLE_MANAGER + " (" + "\n"
                    + MANAGER_COLUMN_USERNAME + " TEXT PRIMARY KEY" + ")";

}
