package com.cinema.avans.cinemaapp.logic.user;

import com.cinema.avans.cinemaapp.domain.Date;
import com.cinema.avans.cinemaapp.domain.Showing;

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
