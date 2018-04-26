package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Date;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class ShowingsAdapter extends ArrayAdapter<Showing> {

    public ShowingsAdapter(Context context, ArrayList<Showing> showings) {
        super(context, 0, showings);

    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Showing showing = getItem(position);
        Date date = showing.getDate();

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_showings, parent, false);

        }

        TextView showingDate = convertView.findViewById(R.id.showingListDate);
        TextView showingTime = convertView.findViewById(R.id.showingListTime);

        showingDate.setText(date.getDateString());
        showingTime.setText(date.getTimeString());

        return convertView;

    }

}
