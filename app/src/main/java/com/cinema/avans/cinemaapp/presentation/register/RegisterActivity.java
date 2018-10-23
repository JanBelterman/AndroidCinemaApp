package com.cinema.avans.cinemaapp.presentation.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.dataAccess.RepositoryFactory;
import com.cinema.avans.cinemaapp.logic.register.RegisterManager;
import com.cinema.avans.cinemaapp.presentation.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    // Manager
    private RegisterManager registerManager;

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
            usernameInput.setText(getIntent().getExtras().getString("USERNAME"));
        }

        // Setup manager
        this.registerManager = new RegisterManager(
                new RepositoryFactory(getApplicationContext()));

        // Setup register button
        Button registerButton = findViewById(R.id.registerRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                Toast.makeText(getApplicationContext(), R.string.userCreated, Toast.LENGTH_LONG).show();
                registerManager.createUser(usernameInput.getText().toString(), passwordConfirmInput.getText().toString());
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
