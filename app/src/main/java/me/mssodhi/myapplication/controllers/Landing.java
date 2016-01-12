package me.mssodhi.myapplication.controllers;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;


import me.mssodhi.myapplication.R;
import me.mssodhi.myapplication.domain.User;

public class Landing extends AppCompatActivity implements View.OnClickListener {
    TextView nameField, ageField, emailField;
    FloatingActionButton floatingActionButton;
    Button logoutButton;
    User user;
    ProfilePictureView profilePictureView;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        // Retrieve the current user from loginActivity
        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        user = (User) bundle.getSerializable("currentUser");

        Toast.makeText(Landing.this, "Welcome " + user.getName(), Toast.LENGTH_LONG).show();

        init();
    }

    private void addDrawerItems() {
        String[] osArray = { "Logout"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(Landing.this, LoginController.class));
                }
            }
        });
    }

    public void init() {
        mDrawerList = (ListView)findViewById(R.id.navList);
        nameField = (TextView) findViewById(R.id.nameField);
        emailField = (TextView) findViewById(R.id.emailField);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(this);

        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        profilePictureView.setProfileId(user.getFacebookID());

        nameField.setText("Name: " + user.getName() + ".");
        emailField.setText("Email: " + user.getEmail());

        addDrawerItems();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Snackbar.make(view, "Contact Manvinder Sodhi at mssodhi@ucdavis.edu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
