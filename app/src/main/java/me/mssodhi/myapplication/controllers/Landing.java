package me.mssodhi.myapplication.controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.mssodhi.myapplication.R;
import me.mssodhi.myapplication.domain.User;

public class Landing extends AppCompatActivity {

    TextView nameField, ageField, emailField;
    User currentUser = new User();
    FloatingActionButton floatingActionButton;

    public void populateUser() {
        currentUser.setEmail("jack_smith@yahoo.com");
        currentUser.setFirstName("Jack");
        currentUser.setLastName("Smith");
        currentUser.setAge(43);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
        populateUser();
        initButtons();
    }

    public void initButtons() {

        ageField = (TextView) findViewById(R.id.ageField);
        nameField = (TextView) findViewById(R.id.nameField);
        emailField = (TextView) findViewById(R.id.emailField);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        nameField.setText("Name: " + currentUser.getFirstName() + " " + currentUser.getLastName() + ".");
        ageField.setText("Age: " + currentUser.getAge());
        emailField.setText("Email: " + currentUser.getEmail());
    }
}
