package me.mssodhi.myapplication.controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.mssodhi.myapplication.R;
import me.mssodhi.myapplication.domain.User;

public class Landing extends AppCompatActivity implements View.OnClickListener {

    TextView nameField, ageField, emailField;
    FloatingActionButton floatingActionButton;
    Button logoutButton;

    User currentUser = new User();


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

        nameField = (TextView) findViewById(R.id.nameField);
        ageField = (TextView) findViewById(R.id.ageField);
        emailField = (TextView) findViewById(R.id.emailField);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

        nameField.setText("Name: " + currentUser.getFirstName() + " " + currentUser.getLastName() + ".");
        ageField.setText("Age: " + currentUser.getAge());
        emailField.setText("Email: " + currentUser.getEmail());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logoutButton:
//                System.out.println("Logout button pressed");
                break;
            case R.id.fab:
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }

    }
}
