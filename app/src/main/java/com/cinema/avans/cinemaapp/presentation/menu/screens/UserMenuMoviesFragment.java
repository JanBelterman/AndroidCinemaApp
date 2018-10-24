package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.Movie;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.logic.additional.UserMenuMoviesManager;
import com.cinema.avans.cinemaapp.presentation.menu.adapters.MovieGridAdapter;
import com.cinema.avans.cinemaapp.presentation.menu.callbacks.UserMenuMoviesPresentation;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMenuMoviesFragment extends Fragment implements UserMenuMoviesPresentation {

    // Manager
    private UserMenuMoviesManager userMenuMoviesManager;
    // Presentation
    private MovieGridAdapter movieGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userMenuMoviesManager = new UserMenuMoviesManager(getActivity(), this);
        userMenuMoviesManager.loadMovies();
        return inflater.inflate(R.layout.fragment_user_hub_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Avoid null exceptions
        if (getView() != null && getView().findViewById(R.id.movieGridView) != null) {
            // Get grid view
            GridView gridView = getView().findViewById(R.id.movieGridView);
            // Create adapter
            movieGridAdapter = new MovieGridAdapter(getContext(), userMenuMoviesManager.getMovies());
            // Set adapter
            gridView.setAdapter(movieGridAdapter);
            // Create onclick listener
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    userMenuMoviesManager.movieClicked(i);
                }
            });
        }
    }

    @Override
    public void startDetailedMovieActivity(Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailedActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movieAdded() {
        movieGridAdapter.notifyDataSetChanged();
    }

}
