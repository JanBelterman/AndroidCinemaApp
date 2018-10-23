package com.cinema.avans.cinemaapp.presentation.user.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.domain.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMenuHomeFragment extends Fragment {

    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.user = ((UserMenuActivity)getActivity()).getUser();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_hub_home, container, false);
    }

}
