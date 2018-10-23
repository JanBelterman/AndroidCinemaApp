package com.cinema.avans.cinemaapp.presentation.user.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);

    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Movie movie = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movies, parent, false);

        }

        if (movie != null) {

            ImageView movieImage = convertView.findViewById(R.id.movieListImage);
            Picasso.with(getContext()).load(movie.getImageUrl()).into(movieImage);
            TextView movieTitle = convertView.findViewById(R.id.movieListTitle);
            movieTitle.setText(movie.getTitle());
            TextView movieRuntime = convertView.findViewById(R.id.movieListRuntime);
            movieRuntime.setText(movie.getRuntime());
            TextView ratingText = convertView.findViewById(R.id.movieListRating);
            ratingText.setText(getContext().getString(R.string.ratingWithSemiColon) + " " + String.valueOf(movie.getRating()));

        }

        return convertView;

    }

}
