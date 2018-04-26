package com.cinema.avans.cinemaapp.frontEnd.logic.user;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Ticket;

import java.util.Comparator;

/**
 * Created by JanBelterman on 25 April 2018
 */

public class ShowingsComparator implements Comparator<Showing> {

    public int compare(Showing showingOne, Showing showingTwo) {

        Date showingOneDate = showingOne.getDate();
        Date showingTwoDate = showingTwo.getDate();

        int compare;

        compare = showingOneDate.getYear() - showingTwoDate.getYear();

        if (compare != 0) {
            return compare;
        }

        compare = showingOneDate.getMonth() - showingTwoDate.getMonth();

        if (compare != 0) {
            return compare;
        }

        compare = showingOneDate.getDay() - showingTwoDate.getDay();

        if (compare != 0) {
            return compare;
        }

        compare = showingOneDate.getHours() - showingTwoDate.getHours();

        if (compare != 0) {
            return compare;
        }

        compare = showingOneDate.getMinutes() - showingTwoDate.getMinutes();

        return compare;

    }

}
