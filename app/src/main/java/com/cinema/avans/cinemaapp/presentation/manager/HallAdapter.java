package com.cinema.avans.cinemaapp.presentation.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.cinema.Hall;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

public class HallAdapter extends ArrayAdapter<Hall> {

    public HallAdapter(Context context, ArrayList<Hall> halls) {
        super(context, 0, halls);

    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Hall hall = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hall, parent, false);

        }

        if (hall != null) {

            TextView hallText = convertView.findViewById(R.id.hallItemHallText);
            hallText.setText(getContext().getString(R.string.hallWithSemiColon) + " " + hall.getHallNr());
            TextView amountOfSeatsText = convertView.findViewById(R.id.hallItemAmountOfSeatsText);
            amountOfSeatsText.setText(getContext().getString(R.string.amountOfSeatsWithSemiColon) + " " + hall.amountOfSeats());

        }

        return convertView;

    }

}
