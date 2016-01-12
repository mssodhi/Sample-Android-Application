package me.mssodhi.myapplication.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import me.mssodhi.myapplication.R;
import me.mssodhi.myapplication.domain.User;

public class Landing extends AppCompatActivity implements View.OnClickListener {

    TextView nameField, ageField, emailField;
    FloatingActionButton floatingActionButton;
    Button logoutButton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        // Retrieve the current user from loginActivity
        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        user = (User) bundle.getSerializable("currentUser");

        Toast.makeText(Landing.this, "welcome " + user.getName(), Toast.LENGTH_LONG).show();

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

        nameField.setText("Name: " + user.getName() + ".");
        emailField.setText("Email: " + user.getEmail());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logoutButton:
                startActivity(new Intent(Landing.this, LoginController.class).putExtra("request", "logout"));
                break;
            case R.id.fab:
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }

    }
}
