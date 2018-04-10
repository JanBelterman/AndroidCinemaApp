package com.cinema.avans.cinemaapp.frontEnd.presentation.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.UserRegister;
import com.cinema.avans.cinemaapp.frontEnd.presentation.logIn.MainActivity;

public class RegisterActivity extends AppCompatActivity {

    // Manager
    private UserRegister userRegister;

    // View
    private TextView usernameInput;
    private TextView passwordInput;
    private TextView passwordConfirmInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //  Setup views
        usernameInput = findViewById(R.id.registerUsernameInput);
        passwordInput = findViewById(R.id.registerPasswordInput);
        passwordConfirmInput = findViewById(R.id.registerPasswordConfirmInput);

        // Get username that was already typed in
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("USERNAME") != null) {
                usernameInput.setText(getIntent().getExtras().getString("USERNAME"));

            }
        }

        // Setup manager
        this.userRegister = new UserRegister(
                new RepositoryFactory(getApplicationContext()));

        // Setup button
        Button registerButton = findViewById(R.id.registerRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());
                String passwordConfirm = String.valueOf(passwordConfirmInput.getText());

                boolean validInputs = true;

                if (username.length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.enterUsernameFirst, Toast.LENGTH_SHORT).show();
                    validInputs = false;

                } else if (password.length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.enterPasswordFirst, Toast.LENGTH_SHORT).show();
                    validInputs = false;

                } else if (passwordConfirm.length() <= 0) {
                    Toast.makeText(getApplicationContext(), R.string.confirmPasswordFirst, Toast.LENGTH_SHORT).show();
                    validInputs = false;

                } else if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(), R.string.passwordsDoNotMatch, Toast.LENGTH_SHORT).show();
                    validInputs = false;

                }

                if (validInputs) {

                    Toast.makeText(getApplicationContext(), R.string.userCreated, Toast.LENGTH_LONG).show();

                    userRegister.createUser(usernameInput.getText().toString(), passwordConfirmInput.getText().toString());

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

    }
}
