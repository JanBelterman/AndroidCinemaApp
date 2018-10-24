package com.cinema.avans.cinemaapp.logic.callbacks;

/**
 * Created by JanBelterman on 24 October 2018
 */
public interface PaymentCallback {

    void success();
    void error(String message);

}
