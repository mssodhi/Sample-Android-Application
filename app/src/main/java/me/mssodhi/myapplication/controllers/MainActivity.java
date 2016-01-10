package me.mssodhi.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.mssodhi.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mainTextView;
    EditText emailField, passwordField;
    Button loginButton;
    FloatingActionButton floatingActionButton;


    String username = "jack", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        mainTextView.setText("Welcome to Android Application");
    }

    public void initButtons() {
        mainTextView = (TextView) findViewById(R.id.mainTextView);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        loginButton = (Button) findViewById(R.id.loginButton);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        loginButton.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

    }

    public void login(View view) {
        Snackbar.make(view, "Authenticating...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        // On successful login, go to the landing page
        if (emailField.getText().toString().equals(username) && passwordField.getText().toString().equals(password)) {
            startActivity(new Intent(MainActivity.this, Landing.class));
        } else {
            mainTextView.setText("Login failed");
            Snackbar.make(view, "Login failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                login(view);
                break;
            case R.id.fab:
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }

    }
}
