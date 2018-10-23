package com.cinema.avans.cinemaapp.presentation.user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 May 2018
 */

public class MovieGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieGridAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Movie movie = movies.get(i);

        if (view == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.grid_item_movie, null);

        }

        ImageView imageView = view.findViewById(R.id.gridItemMovieImage);
        Picasso.with(context).load(movie.getImageUrl()).into(imageView);

        TextView textView = view.findViewById(R.id.gridItemMovieTitle);
        textView.setText(movie.getTitle());

        return view;

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
