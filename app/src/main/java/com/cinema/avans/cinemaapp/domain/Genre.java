package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;

/**
 * Created by JanBelterman on 19 June 2018
 */
public class Genre implements Serializable {

    private int ID;
    private String title;

    public Genre() {

    }

    public Genre(int ID, String title) {

        this.ID = ID;
        this.title = title;

    }

    // SETTERS
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // GETTERS
    public int getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }

}
