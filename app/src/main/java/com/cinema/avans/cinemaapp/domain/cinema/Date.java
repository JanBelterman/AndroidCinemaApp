package com.cinema.avans.cinemaapp.domain.cinema;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Date implements Serializable {

    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;

    Date() {

        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.hours = 0;
        this.minutes = 0;

    }

    // ALTERED SETTER
    public void setDate(String date) {

        String[] dateParts = date.split("-");

        setYear(Integer.parseInt(dateParts[0]));
        setMonth(Integer.parseInt(dateParts[1]));
        setDay(Integer.parseInt(dateParts[2]));
        setHours(Integer.parseInt(dateParts[3]));
        setMinutes(Integer.parseInt(dateParts[4]));

    }

    // SETTERS
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    // GETTERS
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getHours() {
        return hours;
    }
    public int getMinutes() {
        return minutes;
    }

    // Formats the date to display the month in a string
    public String getDateString() {

        return getMonthString(month) + " " + day;

    }

    // Format the time to display an additional 0 when it is the first number (9:00 > 09:00)
    public String getTimeString() {

        String hoursPart = String.valueOf(hours);
        String minutesPart = String.valueOf(minutes);

        if (hours == 0) {
            hoursPart += "0";
        }

        if (minutes == 0) {
            minutesPart += "0";
        }

        return hoursPart + ":" + minutesPart;

    }

    // Get month according to the month number
    private String getMonthString(int month) {

        switch (month) {

            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";

        }

        return "";

    }

    // Formats the date
    @Override
    public String toString() {
        return year + "-" + month + "-" + day + " " + getTimeString();
    }

}
