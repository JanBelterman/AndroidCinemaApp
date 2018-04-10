package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class UserHubActivity extends AppCompatActivity {

    private User user;

    private BottomNavigationView navBar;
    private FrameLayout frameLayout;

    private UserHubHomeFragment userHubHomeFragment;
    private UserHubMoviesFragment userHubMoviesFragment;
    private UserHubUserFragment userHubUserFragment;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_user_hub);

        user = (User) getIntent().getSerializableExtra("USER");

        Log.i("UserHubActivity", "User gotten: " + user);

        navBar = findViewById(R.id.userHubNavBar);
        frameLayout = findViewById(R.id.userHubFrame);

        userHubHomeFragment = new UserHubHomeFragment();
        userHubHomeFragment.setUser(user);
        userHubMoviesFragment = new UserHubMoviesFragment();
        userHubMoviesFragment.setUser(user);
        userHubUserFragment = new UserHubUserFragment();
        userHubUserFragment.setUser(user);

        setFragment(userHubHomeFragment);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navHome:
                        setFragment(userHubHomeFragment);
                        return true;

                    case R.id.navMovies:
                        setFragment(userHubMoviesFragment);
                        return true;

                    case R.id.navUser:
                        setFragment(userHubUserFragment);
                        return true;

                    default:
                        return false;

                }

            }
        });

    }

    private void setFragment(Fragment fragment) {

        // Fix transition orientation
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.userHubFrame, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();

    }

}
