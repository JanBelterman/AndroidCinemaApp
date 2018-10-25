package com.cinema.avans.cinemaapp.presentation.login;

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
import com.cinema.avans.cinemaapp.logic.managers.LoginManager;
import com.cinema.avans.cinemaapp.presentation.register.RegisterActivity;
import com.cinema.avans.cinemaapp.presentation.menu.screens.UserMenuActivity;

public class LoginActivity extends AppCompatActivity implements LoginCallback {

    private LoginManager loginManager;

    // Presentation
    private TextView usernameInput;
    private TextView passwordInput;
    private ImageView loginLogo;
    private ProgressBar loginLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get views
        loginLogo = findViewById(R.id.loginLogo);

        loginLoader = findViewById(R.id.loginLoader);
        loginLoader.setVisibility(View.GONE);
        loginLoader.setScaleX(0);
        loginLoader.setScaleY(0);

        usernameInput = findViewById(R.id.loginUsernameInput);
        passwordInput = findViewById(R.id.loginPasswordInput);

        // Create manager
        this.loginManager = new LoginManager(this, getApplicationContext());

        // Setup action buttons
        setupLoginButton();
        setupRegisterButton();

    }

    private void setupLoginButton() {
        Button loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());
                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.fillUsernameFirst, Toast.LENGTH_LONG).show();
                    return;
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.fillPasswordFirst, Toast.LENGTH_LONG).show();
                    return;
                }
                startLoader();
                loginManager.login(username, password);
            }
        });
    }

    private void setupRegisterButton() {
        TextView registerText = findViewById(R.id.loginRegisterLink);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start register activity (pass username to auto fill for user)
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("USERNAME", usernameInput.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void login(User user) {
        Session.user = user;
        Intent intent = new Intent(LoginActivity.this, UserMenuActivity.class);
        startActivity(intent);
        stopLoader();
    }

    @Override
    public void falseLogin(String message) {
        stopLoader();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    private void startLoader() {
        loginLogo.animate().scaleX(0).scaleY(0).rotation(720).setDuration(750);
        loginLoader.setVisibility(View.VISIBLE);
        loginLoader.animate().scaleX(1).scaleY(1).setDuration(1000);
    }

    private void stopLoader() {
        // Rotate and scale logo back to starting position
        loginLogo.animate().scaleX(1).scaleY(1).rotation(0).setDuration(750);
        // Set loader invisible after the animation time
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginLoader.setVisibility(View.GONE);
            }
        }, 750);
        // Scale loader back to starting position
        loginLoader.animate().scaleX(0).scaleY(0).setDuration(750);
    }

}
