package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie implements Serializable {

    private int movieId;
    private String title;
    private String releaseDate;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String description;
    private String rating;
    private String imageUrl;
    private String production;

    private ArrayList<Showing> showings;

    public Movie() {

        this.movieId = 0;
        this.title = "";
        this.description = "";
        this.runtime = "";
        this.releaseDate = "";
        this.rating = "";
        this.imageUrl = "";
        this.showings = new ArrayList<>();

    }

    // SETTERS
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    public void setGenre(String genre) {
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
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    // GETTERS
    public int getMovieId() {
        return movieId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getRuntime() {
        return runtime;
    }
    public String getGenre() {
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
    public String getRating() {
        return rating;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public ArrayList<Showing> getShowings() {
        return showings;
    }

    public String toString() {
        return "MovieId: " + movieId + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Runtime: " + runtime + "\n" +
                "ReleaseDate: " + releaseDate + "\n" +
                "Rating: " + rating + "\n" +
                "ImageUrl: " + imageUrl;
    }

}
