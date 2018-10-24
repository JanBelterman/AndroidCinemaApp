package com.cinema.avans.cinemaapp.presentation.menu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.Date;
import com.cinema.avans.cinemaapp.domain.Ticket;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class TicketListAdapter extends ArrayAdapter<Ticket> {

    public TicketListAdapter(Context context, ArrayList<Ticket> tickets) {
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
            qrText.setText(String.valueOf(ticket.getID()));
            TextView movieText = convertView.findViewById(R.id.ticketItemMovieText);
            movieText.setText(ticket.getShowing().getMovie().getTitle());
            TextView hallText = convertView.findViewById(R.id.ticketItemHallText);
            hallText.setText(getContext().getString(R.string.hall) + " " + ticket.getShowing().getHallInstance().getHall().getHallNr());
            TextView rowText = convertView.findViewById(R.id.ticketItemRowNr);
            rowText.setText(getContext().getString(R.string.row) + " " + ticket.getSeatInstance().getSeat().getSeatRow().getRowNr());
            TextView seatNrText = convertView.findViewById(R.id.ticketItemSeatNr);
            seatNrText.setText(getContext().getString(R.string.seat) + " " + ticket.getSeatInstance().getSeat().getSeatNr());

            // Display date
            Date date = ticket.getShowing().getDate();

            TextView dateOutput = convertView.findViewById(R.id.ticketDateOutput);
            TextView timeOutput = convertView.findViewById(R.id.ticketTimeOutput);

            dateOutput.setText(date.getDateString());
            timeOutput.setText(date.getTimeString());

        }

        return convertView;

    }

}
