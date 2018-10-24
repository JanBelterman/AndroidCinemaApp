package com.cinema.avans.cinemaapp.presentation.menu.screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class UserMenuActivity extends AppCompatActivity {

    // Fragments
    private UserMenuMoviesFragment userMenuMoviesFragment;
    private UserMenuTicketsFragment userMenuTicketsFragment;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_user_hub);
        // Set session user
        Session.user = (User) getIntent().getSerializableExtra("USER");
        // Set view components
        BottomNavigationView navBar = findViewById(R.id.userHubNavBar);
        userMenuMoviesFragment = new UserMenuMoviesFragment();
        userMenuTicketsFragment = new UserMenuTicketsFragment();
        // Set default
        setFragment(userMenuMoviesFragment);
        // Enable navigation
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navMovies:
                        setFragment(userMenuMoviesFragment);
                        return true;
                    case R.id.navUser:
                        setFragment(userMenuTicketsFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    // Navigation
    private void setFragment(Fragment fragment) {
        // TODO slide animations
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.userHubFrame, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

    // Restrict back presses
    @Override
    public void onBackPressed() {
    }

}
