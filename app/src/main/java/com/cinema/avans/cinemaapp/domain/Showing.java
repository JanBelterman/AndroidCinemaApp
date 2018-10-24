package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Showing implements Serializable {

    private int ID; // Done
    private HallInstance hallInstance; // Done
    private Movie movie; // Done
    private Date date; // Done

    public Showing() {

        this.ID = 0;
        this.hallInstance = new HallInstance();
        this.movie = new Movie();
        this.date = new Date();

    }

    // SETTERS
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setHallInstance(HallInstance hallInstance) {
        this.hallInstance = hallInstance;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
//    public void setDate(Date date) {
//        this.date = date;
//    }
    public void setDate(String date) {
        this.date.setDate(date);

    }

    // GETTERS
    public int getID() {
        return ID;
    }
    public HallInstance getHallInstance() {
        return hallInstance;
    }
    public Movie getMovie() {
        return movie;
    }
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {

        return "ShowingId: " + ID + "\n" +
                "Movie: " + movie + "\n" +
                "Showing is in: " + hallInstance + "\n" +
                "Date: " + date.toString();

    }

}
