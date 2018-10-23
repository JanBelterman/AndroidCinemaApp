package com.cinema.avans.cinemaapp.presentation.user.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.presentation.login.LoginActivity;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class UserMenuActivity extends AppCompatActivity {

    // Presentation
    private UserMenuHomeFragment userMenuHomeFragment;
    private UserMenuMoviesFragment userMenuMoviesFragment;
    private UserMenuTicketsFragment userMenuTicketsFragment;

    // Domain
    private User user;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_user_hub);

        user = (User) getIntent().getSerializableExtra("USER");

        BottomNavigationView navBar = findViewById(R.id.userHubNavBar);
        FrameLayout frameLayout = findViewById(R.id.userHubFrame);

        userMenuHomeFragment = new UserMenuHomeFragment();
        userMenuMoviesFragment = new UserMenuMoviesFragment();
        userMenuTicketsFragment = new UserMenuTicketsFragment();

        setFragment(userMenuHomeFragment);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navHome:
                        setFragment(userMenuHomeFragment);
                        return true;

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

    public User getUser() {
        return user;
    }

    private void setFragment(Fragment fragment) {

        // Fix transition orientation
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.userHubFrame, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserMenuActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
