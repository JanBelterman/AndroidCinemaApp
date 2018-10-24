package com.cinema.avans.cinemaapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie implements Serializable {

    // Movie attributes
    private int ID;
    private String title;
    private String description;
    private Genre genre;
    private String director;
    private String actors;
    private String production;
    private int runtime;
    private Double rating;
    private String releaseDate;
    private String imageUrl;
    // Showings of this movie
    private ArrayList<Showing> showings;

    // Constructor
    public Movie() {

        this.ID = 0;
        this.title = "";
        this.description = "";
        this.genre = new Genre();
        this.runtime = 0;
        this.releaseDate = "";
        this.rating = 0.0;
        this.imageUrl = "";

        this.showings = new ArrayList<>();

    }

    // SETTERS
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }
    public void setProduction(String production) {
        this.production = production;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    // GETTERS
    public int getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getRuntime() {
        return runtime;
    }
    public Genre getGenre() {
        return genre;
    }
    public String getDirector() {
        return director;
    }
    public String getActors() {
        return actors;
    }
    public String getProduction() {
        return production;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public Double getRating() {
        return rating;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public ArrayList<Showing> getShowings() {
        return showings;
    }

    public String toString() {
        return "ID: " + ID + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Runtime: " + runtime + "\n" +
                "ReleaseDate: " + releaseDate + "\n" +
                "Rating: " + rating + "\n" +
                "ImageUrl: " + imageUrl;
    }

}
