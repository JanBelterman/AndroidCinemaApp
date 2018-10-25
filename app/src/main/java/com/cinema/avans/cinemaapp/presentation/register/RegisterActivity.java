package com.cinema.avans.cinemaapp.presentation.register;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.Session;
import com.cinema.avans.cinemaapp.domain.User;
import com.cinema.avans.cinemaapp.logic.managers.RegisterManager;
import com.cinema.avans.cinemaapp.presentation.login.LoginActivity;
import com.cinema.avans.cinemaapp.presentation.menu.screens.UserMenuActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterCallback {

    // Manager
    private RegisterManager registerManager;

    // View
    private TextView usernameInput;
    private TextView passwordInput;
    private TextView passwordConfirmInput;

    private ImageView logo;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //  Setup views
        usernameInput = findViewById(R.id.registerUsernameInput);
        passwordInput = findViewById(R.id.registerPasswordInput);
        passwordConfirmInput = findViewById(R.id.registerPasswordConfirmInput);

        logo = findViewById(R.id.registerLogo);
        progressBar = findViewById(R.id.registerLoader);

        // Get username that was already typed in
        if (getIntent().getExtras() != null) {
            usernameInput.setText(getIntent().getExtras().getString("USERNAME"));
        }

        // Setup manager
        this.registerManager = new RegisterManager(getApplicationContext(),this);

        // Setup register button
        Button registerButton = findViewById(R.id.registerRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard();

                // Get fields
                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());
                String passwordConfirm = String.valueOf(passwordConfirmInput.getText());

                // Validate inputs
                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.enterUsernameFirst, Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.enterPasswordFirst, Toast.LENGTH_SHORT).show();
                    return;
                } else if (passwordConfirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.confirmPasswordFirst, Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(), R.string.passwordsDoNotMatch, Toast.LENGTH_SHORT).show();
                    return;
                }

                startLoader();
                registerManager.createUser(usernameInput.getText().toString(), passwordConfirmInput.getText().toString());

            }
        });

    }

    @Override
    public void login(User user) {
        Session.user = user;
        Intent intent = new Intent(RegisterActivity.this, UserMenuActivity.class);
        startActivity(intent);
        stopLoader();
    }

    @Override
    public void falseRegister(String message) {
        stopLoader();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void startLoader() {
        logo.animate().scaleX(0).scaleY(0).rotation(720).setDuration(750);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.animate().scaleX(1).scaleY(1).setDuration(1000);
    }

    private void stopLoader() {
        // Rotate and scale logo back to starting position
        logo.animate().scaleX(1).scaleY(1).rotation(0).setDuration(750);
        // Set loader invisible after the animation time
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 750);
        // Scale loader back to starting position
        progressBar.animate().scaleX(0).scaleY(0).setDuration(750);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            if (getCurrentFocus() != null) {
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        // TODO pass fields
        finish();
    }

}
