package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Ticket;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class TicketAdapter extends ArrayAdapter<Ticket> {

    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        super(context, 0, tickets);

    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Ticket ticket = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_ticket, parent, false);

        }

        if (ticket != null) {

            // SeatSelector is turned upside down
            TextView qrText = convertView.findViewById(R.id.ticketItemQrCode);
            qrText.setText(String.valueOf(ticket.getTicketId()));
            TextView movieText = convertView.findViewById(R.id.ticketItemMovieText);
            movieText.setText(ticket.getShowing().getMovie().getTitle());
            TextView hallText = convertView.findViewById(R.id.ticketItemHallText);
            hallText.setText(getContext().getString(R.string.hallNrWithSemiColon) + " " + ticket.getShowing().getHallInstance().getHall().getHallNr());
            TextView rowText = convertView.findViewById(R.id.ticketItemRowNr);
            rowText.setText(getContext().getString(R.string.rowNrWithSemiColon) + " " + ticket.getSeatInstance().getSeat().getSeatRow().getRowNr());
            TextView seatNrText = convertView.findViewById(R.id.ticketItemSeatNr);
            seatNrText.setText(getContext().getString(R.string.seatNrWithSemiColon) + " " + ticket.getSeatInstance().getSeat().getSeatNr());

        }

        return convertView;

    }

}
