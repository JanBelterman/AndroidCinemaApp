package com.cinema.avans.cinemaapp.presentation.user;

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
import com.cinema.avans.cinemaapp.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.domain.login.User;
import com.cinema.avans.cinemaapp.logic.user.UserMenuMoviesManager;

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

        // Create manager
        userMenuMoviesManager = new UserMenuMoviesManager(getActivity(), this);
        userMenuMoviesManager.setUser(((UserMenuActivity)getActivity()).getUser());

        return inflater.inflate(R.layout.fragment_user_hub_movies, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create gridView, set adapter and add click listener
        GridView gridView = getView().findViewById(R.id.movieGridView);
        movieGridAdapter = new MovieGridAdapter(getContext(), userMenuMoviesManager.getMovies());
        gridView.setAdapter(movieGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Call manager: delegate onClick of the gridView to manager
                userMenuMoviesManager.movieClicked(i);

            }
        });

    }

    // Called by manager: starts a detailed movie activity
    @Override
    public void startDetailedMovieActivity(User user, Movie movie) {

        Intent intent = new Intent(getContext(), MovieDetailedActivity.class);
        intent.putExtra("MOVIE", movie);
        intent.putExtra("USER", user);
        startActivity(intent);

    }

    // Called by manager: shows error to user
    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

    }

    // Called by manager: refreshes the gridView
    @Override
    public void movieAdded() {
        movieGridAdapter.notifyDataSetChanged();

    }

}
