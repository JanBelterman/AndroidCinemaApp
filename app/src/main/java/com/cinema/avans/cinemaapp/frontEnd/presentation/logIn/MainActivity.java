package com.cinema.avans.cinemaapp.frontEnd.presentation.logIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.GetUserAsync;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInActivity;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInManager;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.UserGottenListener;
import com.cinema.avans.cinemaapp.frontEnd.presentation.manager.ManagerHubActivity;
import com.cinema.avans.cinemaapp.frontEnd.presentation.register.RegisterActivity;
import com.cinema.avans.cinemaapp.frontEnd.presentation.user.UserHubActivity;

public class MainActivity extends AppCompatActivity implements LogInActivity, UserGottenListener {

    // Manager
    private LogInManager logInManager;

    // Views
    private TextView usernameInput;
    private TextView passwordInput;

    private ImageView loginLogo;
    private ProgressBar loginLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupActivity();

        setUpButtons();

    }

    private void setupActivity() {

        // Get views
        loginLogo = findViewById(R.id.loginLogo);

        loginLoader = findViewById(R.id.loginLoader);
        loginLoader.setVisibility(View.GONE);
        loginLoader.setScaleX(0);
        loginLoader.setScaleY(0);

        usernameInput = findViewById(R.id.loginUsernameInput);
        passwordInput = findViewById(R.id.loginPasswordInput);

        //  Setup manager
        this.logInManager = new LogInManager(
                this
                ,new RepositoryFactory(getApplicationContext()));

        insertFakeBackendDataToDatabase();

    }

    private void insertFakeBackendDataToDatabase() {

        Log.i("LogInActivity", "Inserting fake backend data into database");

        RepositoryFactory repositoryFactory = new RepositoryFactory(getApplicationContext());

        // Backend data
        Manager manager = new Manager();
        manager.setUsername("Manager");
        manager.setPassword("12345");
        repositoryFactory.getManagerRepository().createManager(manager);

        User user = new User();
        user.setUsername("User");
        user.setPassword("12345");
        repositoryFactory.getUserRepository().createUser(user);

    }

    private void setUpButtons() {

        // Setup buttons
        Button logInButton = findViewById(R.id.loginLoginButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());

                if (username.length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.fillUsernameFirst, Toast.LENGTH_LONG).show();
                    return;
                } else if (password.length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.fillPasswordFirst, Toast.LENGTH_LONG).show();
                    return;
                }

                // Try logging in, logInManager calls this class back with appropriate method
                if (!logInManager.logIn(username, password)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.invalidCredentials), Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView registerText = findViewById(R.id.loginRegisterLink);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start register activity (pass username to auto fill for user)
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("USERNAME", usernameInput.getText().toString());
                startActivity(intent);

            }
        });

    }

    @Override
    public void managerLogIn(Manager manager) {

        // Go to manager screen
        Intent intent = new Intent(MainActivity.this, ManagerHubActivity.class);
        startActivity(intent);

    }

    @Override
    public void userLogIn(User user) {

        startLoader();

        GetUserAsync getUserAsync = new GetUserAsync(this, new RepositoryFactory(getApplicationContext()).getUserRepository());

        getUserAsync.execute(user);

    }

    @Override
    public void userGotten(User user) {

        // Go to movie list screen for user
        Intent intent = new Intent(MainActivity.this, UserHubActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);

    }

    private void startLoader() {

        loginLogo.animate().scaleX(0).scaleY(0).rotation(720).setDuration(750);
        loginLoader.setVisibility(View.VISIBLE);
        loginLoader.animate().scaleX(1).scaleY(1).setDuration(1000);

    }

}
