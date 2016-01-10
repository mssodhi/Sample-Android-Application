package me.mssodhi.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.mssodhi.myapplication.R;

public class MainActivity extends AppCompatActivity {

    TextView mainTextView;
    FloatingActionButton floatingActionButton;
    Button loginButton;
    EditText emailField, passwordField;
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
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        loginButton = (Button) findViewById(R.id.loginButton);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        /* Controls and method calls */
        /*                           */
        /* Controls and method calls */


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
